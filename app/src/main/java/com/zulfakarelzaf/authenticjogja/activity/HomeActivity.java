package com.zulfakarelzaf.authenticjogja.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zulfakarelzaf.authenticjogja.helper.LocaleHelper;
import com.zulfakarelzaf.authenticjogja.R;
import com.zulfakarelzaf.authenticjogja.fragment.Discover;
import com.zulfakarelzaf.authenticjogja.fragment.Favourite;
import com.zulfakarelzaf.authenticjogja.fragment.More;
import com.zulfakarelzaf.authenticjogja.fragment.Trending;
import com.zulfakarelzaf.authenticjogja.helper.BottomNavHelper;
import com.zulfakarelzaf.authenticjogja.util.PrefManager;

import io.paperdb.Paper;

import static com.zulfakarelzaf.authenticjogja.util.PrefManager.BOTTOM_POS_AFTER_CHANGING_LANGUAGE;


public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(LocaleHelper.onAttach(newBase, Paper.book().read("language").toString()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Paper.init(this);
        LocaleHelper.setLocale(this, Paper.book().read("language").toString());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setItemIconTintList(null);

        toolbar = findViewById(R.id.toolbar);
        PrefManager prefManager = new PrefManager(this);
        if (prefManager.getInt(BOTTOM_POS_AFTER_CHANGING_LANGUAGE) == 3) {
            toolbar.setTitle(getResources().getString(R.string.more));
            prefManager.setInt(BOTTOM_POS_AFTER_CHANGING_LANGUAGE, 1);
        } else {
            toolbar.setTitle(getResources().getString(R.string.trending));
        }
        setSupportActionBar(toolbar);

        BottomNavHelper.disableShiftMode(bottomNavigationView);
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        navHelper(menuView);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame,
                    new Trending()).commit();

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_trending:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,
                        new Trending()).commit();
                toolbar.setTitle(getResources().getString(R.string.trending));
                return true;
            case R.id.navigation_favourite:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,
                        new Favourite()).commit();
                toolbar.setTitle(getResources().getString(R.string.favourite));
                return true;
            case R.id.navigation_discover:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,
                        new Discover()).commit();
                toolbar.setTitle(getResources().getString(R.string.discover));
                return true;
            case R.id.navigation_more:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,
                        new More()).commit();
                toolbar.setTitle(getResources().getString(R.string.more));
                return true;
        }
        return true;
    }

    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_aj);
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
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
    protected void onStart() {
        super.onStart();

        if (bottomNavigationView.getSelectedItemId() == R.id.navigation_favourite) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame,
                    new Favourite()).commit();

        }
    }
}
