package com.zulfakarelzaf.authenticjogja.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.zulfakarelzaf.authenticjogja.adapter.MenuWithThumbnailAdapter;
import com.zulfakarelzaf.authenticjogja.fragment.SubmenuLanguage;
import com.zulfakarelzaf.authenticjogja.helper.BottomNavHelper;
import com.zulfakarelzaf.authenticjogja.model.CategoryFood;
import com.zulfakarelzaf.authenticjogja.presenter.Contract;
import com.zulfakarelzaf.authenticjogja.presenter.SubMenuPresenter;
import com.zulfakarelzaf.authenticjogja.R;

import java.io.Serializable;
import java.util.ArrayList;

public class CulinariesDetailActivity extends AppCompatActivity implements Contract.View,BottomNavigationView.OnNavigationItemSelectedListener{


    private Contract.Presenter presenter;
    private RecyclerView rcItem;
    private ProgressBar pg;
    private Toolbar toolbar;
    private Serializable data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submenu_list);
        presenter = new SubMenuPresenter(this);
        toolbar = findViewById(R.id.toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        toolbar.setNavigationIcon(R.drawable.back);
        rcItem = findViewById(R.id.rcSubmenuItemList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcItem.setLayoutManager(layoutManager);
        data = getIntent().getSerializableExtra("data");
        CategoryFood myData = (CategoryFood)data;
        toolbar.setTitle(myData.getName());

        BottomNavigationView btmNavigationView = findViewById(R.id.submenu_navigation);
        btmNavigationView.setItemIconTintList(null);
        BottomNavHelper.disableShiftMode(btmNavigationView);
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) btmNavigationView.getChildAt(0);
        navHelper(menuView);
        btmNavigationView.setOnNavigationItemSelectedListener(this);

        int categoryId = getIntent().getIntExtra("categoryId", 0);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        pg = findViewById(R.id.progress);
        pg.setVisibility(View.VISIBLE);
        presenter.getCulinnary(categoryId);


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
        MenuWithThumbnailAdapter adapter = new MenuWithThumbnailAdapter(this,data);
        rcItem.setAdapter(adapter);
        pg.setVisibility(View.GONE);
    }

    private void navHelper(BottomNavigationMenuView menuView) {
        int navBarNull = (int) getResources().getDimension(R.dimen.navBar_padding_null);
        int navBar = (int) getResources().getDimension(R.dimen.navBar_padding);

        for (int i = 0; i < menuView.getChildCount(); i++) {
            menuView.setSelected(false);
            final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
            final View texView = menuView.getChildAt(i).findViewById(android.support.design.R.id.text);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 28, displayMetrics);
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 28, displayMetrics);
            iconView.setPadding(navBarNull, navBarNull, navBarNull, navBar);
            iconView.setLayoutParams(layoutParams);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int item_id = item.getItemId();
        Intent intent = new Intent(this,SubMenuActivity.class);
        switch (item_id) {
            case R.id.submenu_culinary:
                intent.putExtra("itemSelected",R.id.submenu_culinary);
                startActivity(intent);
                finish();
                break;
            case R.id.submenu_craft:
                intent.putExtra("itemSelected",R.id.submenu_craft);
                startActivity(intent);
                finish();
                break;
            case R.id.submenu_event:
                intent.putExtra("itemSelected",R.id.submenu_event);
                startActivity(intent);
                finish();
                break;
            case R.id.submenu_language:
                finish();
                intent.putExtra("itemSelected",R.id.submenu_language);
                startActivity(intent);
                break;
        }
        return true;
    }
}
