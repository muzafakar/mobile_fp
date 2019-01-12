package com.zulfakarelzaf.authenticjogja.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zulfakarelzaf.authenticjogja.R;
import com.zulfakarelzaf.authenticjogja.adapter.SubMenuAdapter;
import com.zulfakarelzaf.authenticjogja.common.Common;
import com.zulfakarelzaf.authenticjogja.database.Saved;
import com.zulfakarelzaf.authenticjogja.model.Craft;
import com.zulfakarelzaf.authenticjogja.model.Culinaries;
import com.zulfakarelzaf.authenticjogja.model.Event;
import com.zulfakarelzaf.authenticjogja.util.PrefManager;

import java.util.ArrayList;

/**
 * Created by XAgusart on 5/20/2018.
 * For Educational and Personal purposes
 */

public class Favourite extends Fragment {

    private ArrayList data;
    private ProgressBar pg;
    private TextView tvNoFav;
    private boolean isThereAFavourite;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;

        if (Common.getSavedDatabase().size() < 0) {
            view = inflater.inflate(R.layout.fragment_favourite, container, false);
        } else {
            view = inflater.inflate(R.layout.fragment_favourite_recyler, container, false);
            pg = view.findViewById(R.id.progress);
            pg.setVisibility(View.VISIBLE);
            isThereAFavourite = false;
            tvNoFav = view.findViewById(R.id.tvNoFav);
            final RecyclerView recyclerView = view.findViewById(R.id.rc_favourite);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            data = new ArrayList();


            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (Common.getEvents() != null && Common.getCrafts() != null && Common.getCulinaries() != null) {
                            for (Saved s : Common.getSavedDatabase()) {
                                Log.e("id", s.getItem_id() + "");
                                if (s.getJenis() == 1) {
                                    //craft
                                    for (Craft c : Common.getCrafts()) {
                                        if (c.getId() == s.getItem_id()) {
                                            data.add(c);
                                            isThereAFavourite = true;
                                            break;
                                        }
                                    }

                                } else if (s.getJenis() == 2) {
                                    for (Event e : Common.getEvents()) {
                                        if (e.getId() == s.getItem_id()) {
                                            data.add(e);
                                            isThereAFavourite = true;
                                            break;
                                        }
                                    }

                                } else if (s.getJenis() == 3) {
                                    for (Culinaries c : Common.getCulinaries()) {
                                        if (c.getId() == s.getItem_id()) {
                                            data.add(c);
                                            isThereAFavourite = true;
                                            break;
                                        }
                                    }
                                }
                            }
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    SubMenuAdapter adapter = new SubMenuAdapter(getContext(), data);
                                    recyclerView.setAdapter(adapter);
                                    pg.setVisibility(View.GONE);
                                    if (isThereAFavourite) {
                                        tvNoFav.setVisibility(View.GONE);
                                    } else {
                                        tvNoFav.setVisibility(View.VISIBLE);
                                    }
                                }
                            });
                            break;
                        }
                    }
                }
            });
        }
        return view;
    }


}
