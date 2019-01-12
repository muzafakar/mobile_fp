package com.zulfakarelzaf.authenticjogja.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zulfakarelzaf.authenticjogja.R;

public class OnBoardingAdapter extends PagerAdapter {

    private Context context;

    public OnBoardingAdapter(Context context) {
        this.context = context;
    }

    private int[] slide_images = {
            R.drawable.on_boarding_discover,
            R.drawable.on_boarding_map,
            R.drawable.on_boarding_trivia
    };
    private int[] slide_headings = {
            R.string.onBoardingTitle1,
            R.string.onBoardingTitle2,
            R.string.onBoardingTitle3
    };

    private int[] slide_descs = {
            R.string.onBoardingExplanation1,
            R.string.onBoardingExplanation2,
            R.string.onBoardingExplanation3
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View view = layoutInflater.inflate(R.layout.splash_item, container, false);

        ImageView onBoardingImage = view.findViewById(R.id.onBoardingImage);
        TextView onBoardingTitle = view.findViewById(R.id.onBoardingTitle);
        TextView onBoardingExplanation = view.findViewById(R.id.onBoardingExplanation);

        onBoardingImage.setImageResource(slide_images[position]);
        onBoardingTitle.setText(context.getResources().getString(slide_headings[position]));
        onBoardingExplanation.setText(context.getResources().getString(slide_descs[position]));

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
