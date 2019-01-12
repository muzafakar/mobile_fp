package com.zulfakarelzaf.authenticjogja.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zulfakarelzaf.authenticjogja.R;
import com.zulfakarelzaf.authenticjogja.adapter.DeveloperAdapter;

public class DeveloperActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private DeveloperAdapter developerAdapter;
    private LinearLayout dotsContainer;
    private int currentPage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        developerAdapter = new DeveloperAdapter(this);
        viewPager = findViewById(R.id.developerPager);
        dotsContainer = findViewById(R.id.devDot);
        viewPager.setAdapter(developerAdapter);
        currentPage = 0;
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
