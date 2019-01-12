package com.zulfakarelzaf.authenticjogja.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zulfakarelzaf.authenticjogja.R;
import com.zulfakarelzaf.authenticjogja.adapter.TriviaAdapter;
import com.zulfakarelzaf.authenticjogja.model.Trivia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TriviaActivity extends AppCompatActivity {
    DisplayMetrics displayMetrics = new DisplayMetrics();
    int width = displayMetrics.widthPixels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);
        //hooking the UI
        TextView tvName = findViewById(R.id.trivia_title);
        TextView tvAddress = findViewById(R.id.trivia_location);
        ListView listTrivia = findViewById(R.id.listTrivia);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setTitle("Trivia");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getting the intent
        Intent intent = getIntent();
        String itemName = intent.getStringExtra("name");
        String itemAddress = intent.getStringExtra("address");
        tvName.setText(itemName);
        tvAddress.setText(itemAddress);
        tvAddress.setWidth(width/2);
        ArrayList<String> trivias = intent.getStringArrayListExtra("trivias");
        TriviaAdapter adapter = new TriviaAdapter(this,  trivias);
        listTrivia.setAdapter(adapter);
        listTrivia.setDividerHeight(0);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}


