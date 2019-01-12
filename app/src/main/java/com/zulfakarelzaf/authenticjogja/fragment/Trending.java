package com.zulfakarelzaf.authenticjogja.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.zulfakarelzaf.authenticjogja.adapter.MenuWithThumbnailAdapter;
import com.zulfakarelzaf.authenticjogja.adapter.SubMenuAdapter;
import com.zulfakarelzaf.authenticjogja.common.Common;
import com.zulfakarelzaf.authenticjogja.R;
import com.zulfakarelzaf.authenticjogja.model.Craft;
import com.zulfakarelzaf.authenticjogja.model.Culinaries;
import com.zulfakarelzaf.authenticjogja.model.Event;
import com.zulfakarelzaf.authenticjogja.model.TrendingItem;
import com.zulfakarelzaf.authenticjogja.presenter.Contract;
import com.zulfakarelzaf.authenticjogja.presenter.SubMenuPresenter;
import com.zulfakarelzaf.authenticjogja.retrofit.IAuthenticJogja;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by XAgusart on 5/20/2018.
 */

public class Trending extends Fragment {
    private SubMenuAdapter adp;
    RecyclerView rvTrending;
    ProgressBar pgTrending;
    IAuthenticJogja services;
    ArrayList<TrendingItem> trendingItems;
    private int craftIdTrending;
    private int culinaryIdTrending;
    private int eventIdTrending;
    private Handler handler;
    private List data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trending, container, false);
        final Context context = getActivity().getBaseContext();
        rvTrending = view.findViewById(R.id.trendingRecycler);
        pgTrending = view.findViewById(R.id.progress);
        rvTrending.setLayoutManager(new LinearLayoutManager(context));
        handler = new Handler(Looper.getMainLooper());
        services = Common.createService();
        services.getTrendings().enqueue(new Callback<ArrayList<TrendingItem>>() {
            @Override
            public void onResponse(Call<ArrayList<TrendingItem>> call, Response<ArrayList<TrendingItem>> response) {
                trendingItems = response.body();
                TrendingItem trendingItem = trendingItems.get(0);
                craftIdTrending = trendingItem.getCraftId();
                culinaryIdTrending = trendingItem.getCulinaryId();
                eventIdTrending = trendingItem.getEventId();
                getDatas(craftIdTrending, eventIdTrending, culinaryIdTrending);
            }

            @Override
            public void onFailure(Call<ArrayList<TrendingItem>> call, Throwable t) {

            }
        });

        return view;
    }

    void getDatas(final int craft, final int event, final int culinary) {
       AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                data = new ArrayList();
                while (true) {
                    if (Common.getEvents() != null && Common.getCrafts() != null && Common.getCulinaries() != null) {


                        for (Culinaries c : Common.getCulinaries()) {
                            if (c.getId() == culinary) {
                                data.add(c);
                                break;
                            }
                        }

                        for (Craft c : Common.getCrafts()) {
                            if (c.getId() == craft) {
                                data.add(c);
                                break;
                            }
                        }


                        for (Event e : Common.getEvents()) {
                            if (e.getId() == event) {
                                data.add(e);
                                break;
                            }
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                SubMenuAdapter adapter = new SubMenuAdapter(getContext(), data);
                                rvTrending.setAdapter(adapter);
                                pgTrending.setVisibility(View.GONE);
                            }
                        });
                        break;
                    }
                }
            }
        });


    }
}
