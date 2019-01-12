package com.zulfakarelzaf.authenticjogja.retrofit;

import com.zulfakarelzaf.authenticjogja.model.CategoryFood;
import com.zulfakarelzaf.authenticjogja.model.CategoryLanguages;
import com.zulfakarelzaf.authenticjogja.model.Craft;
import com.zulfakarelzaf.authenticjogja.model.Culinaries;
import com.zulfakarelzaf.authenticjogja.model.Event;
import com.zulfakarelzaf.authenticjogja.model.Languages;
import com.zulfakarelzaf.authenticjogja.model.TrendingItem;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.http.GET;

public interface IAuthenticJogja {

    @GET("events")
    Call<ArrayList<Event>> getEventList();

    @GET("crafts")
    Call<ArrayList<Craft>> getCraftList();

    @GET("category_foods")
    Call<ArrayList<CategoryFood>> getCategory();

    @GET("culinaries")
    Call<ArrayList<Culinaries>> getCulinaries();

    @GET("native_languages")
    Call<ArrayList<Languages>> getLanguages();

    @GET("category_languages")
    Call<ArrayList<CategoryLanguages>> getCategoryLanguages();

    @GET("trendings")
    Call<ArrayList<TrendingItem>> getTrendings();
}
