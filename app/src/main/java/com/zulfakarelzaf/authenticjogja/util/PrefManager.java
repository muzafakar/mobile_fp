package com.zulfakarelzaf.authenticjogja.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zulfakar on 25/05/18.
 * For Educational and Personal purposes
 */

public class PrefManager {
    private SharedPreferences sharedPreferences;
    public static String FIRST_TIME = "firstTime";
    public static String THERE_IS_FAVORITE = "thereIsFavorite";
    public static String BOTTOM_POS_AFTER_CHANGING_LANGUAGE = "BOTTOM_POS_AFTER_CHANGING_LANGUAGE";
    public static String TAP_TARGET_FAVOURITE = "taptargetFavourite";

    private Context context;

    public PrefManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("AuthJogja", Context.MODE_PRIVATE);
    }

    public void setBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public Boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void setInt(String key, int value){
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public int getInt(String key){
        return sharedPreferences.getInt(key, 0);
    }
}
