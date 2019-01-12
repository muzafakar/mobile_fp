package com.zulfakarelzaf.authenticjogja.activity;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.zulfakarelzaf.authenticjogja.adapter.ListLanguageAdapter;
import com.zulfakarelzaf.authenticjogja.model.CategoryLanguages;
import com.zulfakarelzaf.authenticjogja.presenter.Contract;
import com.zulfakarelzaf.authenticjogja.presenter.SubMenuPresenter;
import com.zulfakarelzaf.authenticjogja.R;

import java.io.Serializable;
import java.util.ArrayList;

public class LanguageDetailActivity extends AppCompatActivity implements Contract.View{
    private ProgressBar pg;
    private Toolbar toolbar;
    private RecyclerView rcLanguage;
    private Contract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_language);
        presenter = new SubMenuPresenter(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        Serializable data = getIntent().getSerializableExtra("data");
        CategoryLanguages categoryLanguages = (CategoryLanguages) data;
        toolbar.setTitle(((CategoryLanguages) data).getName());
        pg = findViewById(R.id.progress);
        pg.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        rcLanguage = findViewById(R.id.rcLanguage);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcLanguage.setLayoutManager(layoutManager);
        presenter.getLanguange(categoryLanguages.getId());
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

    @Override
    public void onCompleteCall(ArrayList data) {
        ListLanguageAdapter adapter = new ListLanguageAdapter(this,data);
        rcLanguage.setAdapter(adapter);
        pg.setVisibility(View.GONE);
    }
}
