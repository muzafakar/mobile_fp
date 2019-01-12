package com.zulfakarelzaf.authenticjogja.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zulfakarelzaf.authenticjogja.R;
import com.zulfakarelzaf.authenticjogja.util.PrefManager;



public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        final PrefManager prefManager = new PrefManager(this);
        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!prefManager.getBoolean(PrefManager.FIRST_TIME)) {
                    startActivity(new Intent(SplashScreen.this, OnBoardingActivity.class));
                } else {
                    startActivity(new Intent(SplashScreen.this, HomeActivity.class));
                }
                finish();
            }
        }, 1500);
    }
}