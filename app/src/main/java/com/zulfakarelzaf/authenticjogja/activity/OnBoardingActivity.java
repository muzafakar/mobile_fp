package com.zulfakarelzaf.authenticjogja.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zulfakarelzaf.authenticjogja.adapter.OnBoardingAdapter;
import com.zulfakarelzaf.authenticjogja.R;
import com.zulfakarelzaf.authenticjogja.util.PrefManager;

import de.javakaffee.kryoserializers.ArraysAsListSerializer;

public class OnBoardingActivity extends AppCompatActivity {

    private LinearLayout dotsContainer;
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        ImageView arrowSkip = findViewById(R.id.arrowSkip);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        OnBoardingAdapter onBoardingAdapter = new OnBoardingAdapter(this);
        ViewPager mSlideViewPager = findViewById(R.id.viewPager);
        dotsContainer = findViewById(R.id.dotsLayout);
        currentPage = 0;

        arrowSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnBoardingActivity.this, HomeActivity.class));
                PrefManager prefManager = new PrefManager(OnBoardingActivity.this);
                prefManager.setBoolean(PrefManager.FIRST_TIME, true);
                finish();
            }
        });


        mSlideViewPager.setAdapter(onBoardingAdapter);
        mSlideViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addDots(position);
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        addDots(currentPage);
    }

    private void addDots(int position) {
        TextView[] mDots = new TextView[3];
        dotsContainer.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setPadding(2, 2, 2, 2);
            mDots[i].setTextSize(32f);
            mDots[i].setTextColor(getResources().getColor(R.color.abu));
            dotsContainer.addView(mDots[i]);
        }
        mDots[position].setTextColor(getResources().getColor(R.color.merah4));
    }

}
