package com.zulfakarelzaf.authenticjogja.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zulfakarelzaf.authenticjogja.fragment.FragmentSlider;

import java.util.ArrayList;
import java.util.List;

public class SliderPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "SliderPagerAdapter";

    List<Fragment> mFrags = new ArrayList<>();

    public SliderPagerAdapter(FragmentManager fm, List<Fragment> frags) {
        super(fm);
        mFrags = frags;
    }

    @Override
    public Fragment getItem(int position) {
        int index = position % mFrags.size();
        return FragmentSlider.newInstance(mFrags.get(index).getArguments().getString("params"));
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

}