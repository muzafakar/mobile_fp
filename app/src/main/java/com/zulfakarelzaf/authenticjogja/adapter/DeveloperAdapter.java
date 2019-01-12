package com.zulfakarelzaf.authenticjogja.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zulfakarelzaf.authenticjogja.R;
import com.zulfakarelzaf.authenticjogja.util.Const;

import de.hdodenhof.circleimageview.CircleImageView;

public class DeveloperAdapter extends PagerAdapter {

    private Context context;

    public DeveloperAdapter(Context context) {
        this.context = context;
    }

    private String[] developerImage = {
            Const.developer1,
            Const.developer2,
            Const.developer3
    };
    private String[] developerName = {
            "Muhammad Zulfakar",
            "Yusril",
            "Agung Suryo Sundoro"
    };
    private String[] developerNpm = {
            "16.11.0500",
            "16.11.0503",
            "16.11.0493"
    };


    @Override
    public int getCount() {
        return developerImage.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View view = layoutInflater.inflate(R.layout.fragment_developer, container, false);

        CircleImageView devImage = view.findViewById(R.id.iImg1);
        TextView devName = view.findViewById(R.id.name1);
        TextView devNpm = view.findViewById(R.id.npm1);

        Picasso.get().load(developerImage[position]).fit().into(devImage);
        devName.setText(developerName[position]);
        devNpm.setText(developerNpm[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((CardView) object);
    }
}
