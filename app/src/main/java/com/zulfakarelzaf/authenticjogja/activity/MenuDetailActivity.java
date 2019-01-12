package com.zulfakarelzaf.authenticjogja.activity;

import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.squareup.picasso.Target;
import com.zulfakarelzaf.authenticjogja.adapter.SliderPagerAdapter;
import com.squareup.picasso.Picasso;
import com.zulfakarelzaf.authenticjogja.application.App;
import com.zulfakarelzaf.authenticjogja.common.Common;
import com.zulfakarelzaf.authenticjogja.database.DaoSession;
import com.zulfakarelzaf.authenticjogja.database.Saved;
import com.zulfakarelzaf.authenticjogja.fragment.FragmentSlider;
import com.zulfakarelzaf.authenticjogja.helper.BottomNavHelper;
import com.zulfakarelzaf.authenticjogja.model.Craft;
import com.zulfakarelzaf.authenticjogja.model.Culinaries;
import com.zulfakarelzaf.authenticjogja.model.Event;
import com.zulfakarelzaf.authenticjogja.model.ImageUrl;
import com.zulfakarelzaf.authenticjogja.model.ItemModel;
import com.zulfakarelzaf.authenticjogja.R;
import com.zulfakarelzaf.authenticjogja.model.Trivia;
import com.zulfakarelzaf.authenticjogja.util.BitmapUtil;
import com.zulfakarelzaf.authenticjogja.util.PrefManager;
import com.zulfakarelzaf.authenticjogja.util.SliderIndicator;
import com.zulfakarelzaf.authenticjogja.util.SliderView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuDetailActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private TextView title;
    private TextView price;
    private TextView detail;
    private ImageView imgItem;
    private String Messsages;
    private String jenis;
    private DaoSession daoSession;
    private Saved saved;
    private String itemTitle;
    private String itemAddress;
    private double lat, lon;
    private boolean isItemSaved;
    private BottomNavigationView bottomNavigationView;
    private String urlImage;
    private ArrayList<Trivia> triviaObj;
    private ArrayList<String> triviaStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);
        daoSession = ((App) getApplication()).getDaoSession();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        title = findViewById(R.id.menu_title);
        price = findViewById(R.id.category_price);
        imgItem = findViewById(R.id.menu_detail_image);
        detail = findViewById(R.id.menu_detail_textdetail);
        //recieving intent
        saved = new Saved(); //entity object
        Serializable data = getIntent().getSerializableExtra("data");
        if (data instanceof ItemModel) {
            ItemModel m = (ItemModel) data;
            title.setText(m.getTitle());
            price.setText("");

        } else if (data instanceof Craft) {
            Craft c = (Craft) data;
            triviaObj = c.getTrivia();
            triviaStr = extractTriviaString(triviaObj);
            itemTitle = c.getName();
            itemAddress = c.getAddress();
            title.setText(itemTitle);
            price.setText("");
            detail.setText(c.getDescription());
            lat = c.getLatitude();
            lon = c.getLongitude();
            Messsages = c.getName();
            saved.setJenis(1);
            saved.setItem_id(c.getId());
            urlImage = c.getImage().getUrl();
            jenis = "Craft";
        } else if (data instanceof Event) {
            Event e = (Event) data;
            triviaObj = e.getTrivia();
            triviaStr = extractTriviaString(triviaObj);
            itemTitle = e.getName();
            itemAddress = e.getAddress();
            title.setText(itemTitle);
            price.setText(((Event) data).getDate());
            detail.setText(e.getDescription());
            lat = e.getLatitude();
            lon = e.getLongitude();
            Messsages = e.getName();
            saved.setJenis(2);
            saved.setItem_id(e.getId());
            ImageUrl imageUrl = ((Event) data).getImage();
            urlImage = imageUrl.getUrl();
            urlImage = e.getImage().getUrl();
            jenis = "Event";
        } else if (data instanceof Culinaries) {
            Culinaries c = (Culinaries) data;
            triviaObj = c.getTrivia();
            triviaStr = extractTriviaString(triviaObj);
            itemTitle = c.getName();
            itemAddress = c.getAddress();
            title.setText(itemTitle);
            price.setText("");
            detail.setText(c.getDescription());
            lat = c.getLatitude();
            lon = c.getLongitude();
            Messsages = c.getName();
            saved.setJenis(3);
            saved.setItem_id(c.getId());
            urlImage = c.getImageUrl().getUrl();
            jenis = "Cullinary";
        }


        Picasso.get().load(urlImage)
                .fit()
                .centerCrop()
                .into(imgItem);


        Toolbar toolbar = findViewById(R.id.menu_detail_toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        bottomNavigationView = findViewById(R.id.menu_detail_botom_nav);


        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_detail_botom_nav);
        isItemSaved = chekItemSaved();
        bottomNavigationView.setSelectedItemId(R.id.detail_add_to_fav);
        if(isItemSaved){
            bottomNavigationView.getMenu().findItem(R.id.detail_add_to_fav).setTitle(R.string.remove);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setItemIconTintList(null);
        BottomNavHelper.disableShiftModeAndZoom(bottomNavigationView);
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        int navBarNull = (int) getResources().getDimension(R.dimen.navBar_padding_null);
        int navBar = (int) getResources().getDimension(R.dimen.navBar_padding);

        for (int i = 1; i < bottomNavigationView.getMenu().size(); i++) {
            bottomNavigationView.getMenu().getItem(i).setCheckable(false);
        }
        for (int i = 0; i < menuView.getChildCount(); i++) {
            menuView.getChildAt(i).setSelected(false);
            final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 28, displayMetrics);
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 28, displayMetrics);
            iconView.setPadding(navBarNull, navBarNull, navBarNull, navBar);
            iconView.setLayoutParams(layoutParams);
        }

        PrefManager prefManager = new PrefManager(this);
        if (!prefManager.getBoolean(PrefManager.TAP_TARGET_FAVOURITE)) {
            ttSave();
            prefManager.setBoolean(PrefManager.TAP_TARGET_FAVOURITE, true);
        }
    }

    private boolean chekItemSaved() {
        for (Saved s : Common.getSavedDatabase()) {
            //checking item exist or not in databases
            //return true if exist, save item to database if not
            if (s.getItem_id() == saved.getItem_id() && s.getJenis() == saved.getJenis()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // getMenuInflater().inflate(R.menu.search_action, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.detail_add_to_fav:
                if (isItemSaved) {
                    for (Saved s : Common.getSavedDatabase()) {
                        if (s.getItem_id() == saved.getItem_id()) {
                            daoSession.delete(s);
                            break;
                        }
                    }
                    Common.setSavedDatabase(daoSession.loadAll(Saved.class));
                    bottomNavigationView.getMenu().findItem(R.id.detail_add_to_fav).setTitle(R.string.add_to_favourite);
                    isItemSaved = !isItemSaved;
                    return true;
                } else {
                    daoSession.insert(saved); // reload all data from database
                    Common.setSavedDatabase(daoSession.loadAll(Saved.class));
                    Toast.makeText(this, R.string.simpan, Toast.LENGTH_SHORT).show();
                    isItemSaved = !isItemSaved;
                    bottomNavigationView.getMenu().findItem(R.id.detail_add_to_fav).setTitle(R.string.remove);
                }
                return true;
            case R.id.detail_map:
                launchMap(lat, lon);
                return true;
            case R.id.detail_share:
//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.putExtra(Intent.EXTRA_TEXT, String.format("[%s] found the Detail for this Craft on Authentic Jogja App, Download : http://auth-jogja.herokuapp.com/", Messsages));
//                sendIntent.setType("text/plain");
//                startActivity(Intent.createChooser(sendIntent, "Share to your social media"));
                Picasso.get().load(urlImage).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("image/*");
                        i.putExtra(Intent.EXTRA_TEXT, String.format("[%s] found the Detail for this %s on Authentic Jogja App, Download : http://auth-jogja.herokuapp.com/", Messsages, jenis));
                        i.putExtra(Intent.EXTRA_STREAM, BitmapUtil.getLocalBitmapUri(BitmapUtil.mark(bitmap, "© Authentic Jogja", getApplicationContext()), getApplicationContext()));
                        startActivity(Intent.createChooser(i, "Share Image"));
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
                return true;
            case R.id.Trivia:
                Intent intent = new Intent(this, TriviaActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("trivias", triviaStr);
                intent.putExtra("name", itemTitle);
                intent.putExtra("address", itemAddress);
                startActivity(intent);
                return true;
        }
        return false;
    }

    private void launchMap(double latitude, double longitude) {
        String label = Messsages;
        String uriBegin = "geo:" + latitude + "," + longitude;
        String query = latitude + "," + longitude + "(" + label + ")";
        String encodedQuery = Uri.encode(query);
        String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
        Uri uri = Uri.parse(uriString);
        Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, uri);
        startActivity(mapIntent);
    }

    private ArrayList<String> extractTriviaString(ArrayList<Trivia> triviaOBJ) {
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (Trivia t : triviaOBJ) {
            stringArrayList.add(t.getName());
        }

        return stringArrayList;
    }

    private void ttSave() {
        TapTargetView.showFor(this, TapTarget.forView(findViewById(R.id.detail_add_to_fav), getString(R.string.tt_favourite))
                .outerCircleColor(R.color.merah4)
                .outerCircleAlpha(0.96f)
                .targetCircleColor(R.color.putih)
                .titleTextSize(20)
                .titleTextColor(R.color.putih)
                .descriptionTextSize(10)
                .descriptionTextColor(R.color.merah4)
                .drawShadow(true)
                .cancelable(false)
                .tintTarget(true)
                .targetRadius(60),
                new TapTargetView.Listener(){
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);
                        ttShare();
                    }
                }
        );
    }

    private void ttShare() {
        TapTargetView.showFor(this, TapTarget.forView(findViewById(R.id.detail_share), getString(R.string.tt_share))
                .outerCircleColor(R.color.merah4)
                .outerCircleAlpha(0.96f)
                .targetCircleColor(R.color.putih)
                .titleTextSize(20)
                .titleTextColor(R.color.putih)
                .descriptionTextSize(10)
                .descriptionTextColor(R.color.merah4)
                .drawShadow(true)
                .cancelable(false)
                .tintTarget(true)
                .targetRadius(60),
                new TapTargetView.Listener(){
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);
                        ttTrivia();
                    }
                }
        );
    }

    private void ttTrivia() {
        TapTargetView.showFor(this, TapTarget.forView(findViewById(R.id.Trivia), getString(R.string.tt_trivia))
                .outerCircleColor(R.color.merah4)
                .outerCircleAlpha(0.96f)
                .targetCircleColor(R.color.putih)
                .titleTextSize(20)
                .titleTextColor(R.color.putih)
                .descriptionTextSize(10)
                .descriptionTextColor(R.color.merah4)
                .drawShadow(true)
                .cancelable(false)
                .tintTarget(true)
                .targetRadius(60),
                new TapTargetView.Listener(){
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);
                        ttMap();
                    }
                }
        );
    }

    private void ttMap() {
        TapTargetView.showFor(this, TapTarget.forView(findViewById(R.id.detail_map), getString(R.string.tt_map))
                .outerCircleColor(R.color.merah4)
                .outerCircleAlpha(0.96f)
                .targetCircleColor(R.color.putih)
                .titleTextSize(20)
                .titleTextColor(R.color.putih)
                .descriptionTextSize(10)
                .descriptionTextColor(R.color.merah4)
                .drawShadow(true)
                .cancelable(false)
                .tintTarget(true)
                .targetRadius(60)
        );
    }

}
