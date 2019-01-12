package com.zulfakarelzaf.authenticjogja.retrofit;

import android.support.annotation.NonNull;

import com.zulfakarelzaf.authenticjogja.common.Common;
import com.zulfakarelzaf.authenticjogja.model.CategoryFood;
import com.zulfakarelzaf.authenticjogja.model.CategoryLanguages;
import com.zulfakarelzaf.authenticjogja.model.Craft;
import com.zulfakarelzaf.authenticjogja.model.Culinaries;
import com.zulfakarelzaf.authenticjogja.model.Event;
import com.zulfakarelzaf.authenticjogja.model.Languages;
import com.zulfakarelzaf.authenticjogja.model.TrendingItem;
import com.zulfakarelzaf.authenticjogja.presenter.Contract;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Intractor implements Contract.Intractor{

    private Contract.Presenter presenter;
    private IAuthenticJogja mService;
    private IAuthenticJogja mServiceTester;


    public Intractor(Contract.Presenter presenter) {
        this.presenter = presenter;
        mService = Common.createService();
        mServiceTester = Common.createServiceTester();
    }

    @Override
    public void getEvent() {
            mService.getEventList().enqueue(new Callback<ArrayList<Event>>() {
                @Override
                public void onResponse(@NonNull Call<ArrayList<Event>> call, @NonNull Response<ArrayList<Event>> response) {
                    presenter.onFinishCall(response.body());
                }

                @Override
                public void onFailure(@NonNull Call<ArrayList<Event>> call, @NonNull Throwable t) {
                        t.printStackTrace();
                }
            });
    }

    @Override
    public void getCraft() {


            mService.getCraftList().enqueue(new Callback<ArrayList<Craft>>() {
                @Override
                public void onResponse(@NonNull Call<ArrayList<Craft>> call, @NonNull Response<ArrayList<Craft>> response) {
                    presenter.onFinishCall(response.body());
                }

                @Override
                public void onFailure(@NonNull Call<ArrayList<Craft>> call, @NonNull Throwable t) {
                    presenter.onFailurCall(t);
                }
            });


    }

    @Override
    public void getCulinnary(final int id) {
            mService.getCulinaries().enqueue(new Callback<ArrayList<Culinaries>>() {
                @Override
                public void onResponse(@NonNull Call<ArrayList<Culinaries>> call, @NonNull Response<ArrayList<Culinaries>> response) {
                    ArrayList<Culinaries> data = new ArrayList();
                    for(Culinaries l : response.body() ){
                        if(l.getCategoryFood().getId() == id){
                            data.add(l);
                        }
                    }
                    presenter.onFinishCall(data);
                }

                @Override
                public void onFailure(@NonNull Call<ArrayList<Culinaries>> call, @NonNull Throwable t) {
                    presenter.onFailurCall(t);
                }
            });

    }

    @Override
    public void getLanguange(final int id) {
        mServiceTester.getLanguages().enqueue(new Callback<ArrayList<Languages>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Languages>> call, @NonNull Response<ArrayList<Languages>> response) {
                ArrayList<Languages> data = new ArrayList<>();
                for(Languages l :response.body() ){
                    if(l.getCategory_language().getId() == id){
                        data.add(l);
                    }
                }
                presenter.onFinishCall(data);
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Languages>> call, @NonNull Throwable t) {
                presenter.onFailurCall(t);
            }
        });
    }

    @Override
    public void getCategory() {
        mService.getCategory().enqueue(new Callback<ArrayList<CategoryFood>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<CategoryFood>> call, @NonNull Response<ArrayList<CategoryFood>> response) {
                presenter.onFinishCall(response.body());
            }
            @Override
            public void onFailure(@NonNull Call<ArrayList<CategoryFood>> call, @NonNull Throwable t) {
            }
        });
    }

    @Override
    public void getCategoryLanguage() {
            mServiceTester.getCategoryLanguages().enqueue(new Callback<ArrayList<CategoryLanguages>>() {
                @Override
                public void onResponse(@NonNull Call<ArrayList<CategoryLanguages>> call, @NonNull Response<ArrayList<CategoryLanguages>> response) {
                    presenter.onFinishCall(response.body());
                }

                @Override
                public void onFailure(@NonNull Call<ArrayList<CategoryLanguages>> call, @NonNull Throwable t) {

                }
            });
    }

    @Override
    public void getTrending() {
        mService.getTrendings().enqueue(new Callback<ArrayList<TrendingItem>>() {
            @Override
            public void onResponse(Call<ArrayList<TrendingItem>> call, Response<ArrayList<TrendingItem>> response) {
                presenter.onFinishCall(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<TrendingItem>> call, Throwable t) {

            }
        });
    }
}
