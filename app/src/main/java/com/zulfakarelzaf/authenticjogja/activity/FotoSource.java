package com.zulfakarelzaf.authenticjogja.activity;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zulfakarelzaf.authenticjogja.R;
import com.zulfakarelzaf.authenticjogja.util.SourceBank;

public class FotoSource extends AppCompatActivity {
    private ListView listView;
    private SourceBank sourceBank;
    private final String PATH = "sumberfoto.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_source);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        toolbar.setTitle("Source");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = findViewById(R.id.listSource);
        sourceBank = new SourceBank(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.source_item,sourceBank.getAllSource(PATH));
        listView.setAdapter(adapter);
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
