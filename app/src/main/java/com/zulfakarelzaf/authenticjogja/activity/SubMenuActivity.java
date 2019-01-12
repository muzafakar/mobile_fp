package com.zulfakarelzaf.authenticjogja.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.zulfakarelzaf.authenticjogja.adapter.MenuWithThumbnailAdapter;
import com.zulfakarelzaf.authenticjogja.common.Common;
import com.zulfakarelzaf.authenticjogja.fragment.SubmenuCraft;
import com.zulfakarelzaf.authenticjogja.fragment.SubmenuCulinnary;
import com.zulfakarelzaf.authenticjogja.fragment.SubmenuEvent;
import com.zulfakarelzaf.authenticjogja.fragment.SubmenuLanguage;
import com.zulfakarelzaf.authenticjogja.helper.LocaleHelper;
import com.zulfakarelzaf.authenticjogja.model.Craft;
import com.zulfakarelzaf.authenticjogja.model.Culinaries;
import com.zulfakarelzaf.authenticjogja.model.Event;
import com.zulfakarelzaf.authenticjogja.model.ItemModel;
import com.zulfakarelzaf.authenticjogja.R;
import com.zulfakarelzaf.authenticjogja.fragment.Discover;
import com.zulfakarelzaf.authenticjogja.helper.BottomNavHelper;
import com.zulfakarelzaf.authenticjogja.model.Languages;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class SubMenuActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private int itemSelected;
    private Toolbar toolbar;
    private String subMenuTitle;
    private android.support.v7.widget.SearchView searchView;
    private SubmenuEvent fragmentEvent;
    private SubmenuCraft fragmentCraft;
    private SubmenuCulinnary fragmentCulinary;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(LocaleHelper.onAttach(newBase, Paper.book().read("language").toString()));
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Paper.init(this);
        LocaleHelper.setLocale(this, Paper.book().read("language").toString());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        fragmentEvent = new SubmenuEvent();
        fragmentCraft = new SubmenuCraft();
        fragmentCulinary = new SubmenuCulinnary();
        subMenuTitle = getIntent().getStringExtra(Discover.SUB_MENU_TITLE);

        if (subMenuTitle != null) {
            itemSelected = subMenuTitle.equals(getResources().getString(R.string.culinary)) ? R.id.submenu_culinary :
                    subMenuTitle.equals(getResources().getString(R.string.craft)) ? R.id.submenu_craft : subMenuTitle.equals(getResources().getString(R.string.events)) ?
                            R.id.submenu_event : R.id.submenu_language;
        } else {
            itemSelected = getIntent().getIntExtra("itemSelected", R.id.submenu_culinary);
            subMenuTitle = getString(R.string.culinary);
        }

        BottomNavigationView btmNavigationView = findViewById(R.id.submenu_navigation);
        btmNavigationView.setItemIconTintList(null);
        BottomNavHelper.disableShiftMode(btmNavigationView);
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) btmNavigationView.getChildAt(0);
        navHelper(menuView);


        btmNavigationView.setSelectedItemId(itemSelected);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(subMenuTitle);
        toolbar.setNavigationIcon(R.drawable.back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        onNavigationItemSelected(btmNavigationView.getMenu().findItem(itemSelected));
        btmNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_action, menu);
        final MenuItem search = menu.findItem(R.id.search);
        searchView = (android.support.v7.widget.SearchView) search.getActionView();
        searchView.setQueryHint("Input disini");


        final ArrayList result = new ArrayList();

        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {

            ArrayList result = new ArrayList();

            @Override
            public boolean onQueryTextSubmit(String query) {
                // Toast.makeText(SubMenuActivity.this, ""+query, Toast.LENGTH_SHORT).show();
                fragmentEvent.setData(result);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                switch (itemSelected) {
                    case R.id.submenu_event:
                        for (Event e : Common.getEvents()) {
                            if (e.getName().toLowerCase().contains(newText)) {
                                result.add(e);
                            }
                        }
                        fragmentEvent.setData(result);
                        result = new ArrayList();
                        break;
                    case R.id.submenu_culinary:
                        if (newText.equals("")) {
                            fragmentCulinary.restoreAdapter();
                            break;
                        }
                        for (Culinaries e : Common.getCulinaries()) {
                            if (e.getName().toLowerCase().contains(newText)) {
                                result.add(e);
                            }
                        }
                        fragmentCulinary.setData(result);
                        result = new ArrayList();
                        break;
                    case R.id.submenu_craft:
                        for (Craft e : Common.getCrafts()) {
                            if (e.getName().toLowerCase().contains(newText)) {
                                result.add(e);
                            }
                        }
                        fragmentCraft.setData(result);
                        result = new ArrayList();
                        break;
                }

                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int item_id = item.getItemId();
        String submenuName = "";
        switch (item_id) {
            case R.id.submenu_culinary:
                itemSelected = R.id.submenu_culinary;
                submenuName = getResources().getString(R.string.culinary);
                getSupportFragmentManager().beginTransaction().replace(R.id.submenu_fragment, fragmentCulinary).commit();
                break;
            case R.id.submenu_craft:
                itemSelected = R.id.submenu_craft;
                submenuName = getResources().getString(R.string.craft);
                getSupportFragmentManager().beginTransaction().replace(R.id.submenu_fragment, fragmentCraft).commit();
                break;
            case R.id.submenu_event:
                itemSelected = R.id.submenu_event;
                submenuName = getResources().getString(R.string.events);
                getSupportFragmentManager().beginTransaction().replace(R.id.submenu_fragment, fragmentEvent).commit();
                break;
            case R.id.submenu_language:
                submenuName = getResources().getString(R.string.native_language);
                getSupportFragmentManager().beginTransaction().replace(R.id.submenu_fragment, new SubmenuLanguage()).commit();
                break;
        }
        toolbar.setTitle(submenuName);
        return true;
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


}
