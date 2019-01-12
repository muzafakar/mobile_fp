package com.zulfakarelzaf.authenticjogja.application;

import android.app.Application;

import com.zulfakarelzaf.authenticjogja.common.Common;
import com.zulfakarelzaf.authenticjogja.database.DaoMaster;
import com.zulfakarelzaf.authenticjogja.database.DaoSession;
import com.zulfakarelzaf.authenticjogja.database.Saved;
import com.zulfakarelzaf.authenticjogja.model.Craft;
import com.zulfakarelzaf.authenticjogja.model.Culinaries;
import com.zulfakarelzaf.authenticjogja.model.Event;
import com.zulfakarelzaf.authenticjogja.retrofit.IAuthenticJogja;

import org.greenrobot.greendao.database.Database;


import java.util.ArrayList;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by XAgusart on 6/2/2018.
 */

public class App extends Application {

    private DaoSession daoSession;
    IAuthenticJogja services;

    @Override
    public void onCreate() {
        super.onCreate();
        Paper.init(this);
        if (!Paper.book().exist("language")) {
            Paper.book().write("language", "en");
        }


        services = Common.createService();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "saved-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        Common.setSavedDatabase(daoSession.loadAll(Saved.class));
        initiateData();
    }

    void initiateData() {
        services.getCulinaries().enqueue(new Callback<ArrayList<Culinaries>>() {
            @Override
            public void onResponse(Call<ArrayList<Culinaries>> call, Response<ArrayList<Culinaries>> response) {
                Common.setCulinaries(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Culinaries>> call, Throwable t) {

            }
        });

        services.getCraftList().enqueue(new Callback<ArrayList<Craft>>() {
            @Override
            public void onResponse(Call<ArrayList<Craft>> call, Response<ArrayList<Craft>> response) {
                Common.setCrafts(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Craft>> call, Throwable t) {

            }
        });

        services.getEventList().enqueue(new Callback<ArrayList<Event>>() {
            @Override
            public void onResponse(Call<ArrayList<Event>> call, Response<ArrayList<Event>> response) {
                Common.setEvents(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Event>> call, Throwable t) {

            }
        });
    }


    public DaoSession getDaoSession() {
        return daoSession;
    }
}
