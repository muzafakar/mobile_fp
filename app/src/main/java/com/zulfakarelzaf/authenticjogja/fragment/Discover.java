package com.zulfakarelzaf.authenticjogja.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zulfakarelzaf.authenticjogja.R;
import com.zulfakarelzaf.authenticjogja.activity.SubMenuActivity;


public class Discover extends Fragment implements View.OnClickListener {
    public static String SUB_MENU_TITLE = "subMenuTitle";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        view.findViewById(R.id.culinary).setOnClickListener(this);
        view.findViewById(R.id.craft).setOnClickListener(this);
        view.findViewById(R.id.events).setOnClickListener(this);
        view.findViewById(R.id.nativeLanguage).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity().getBaseContext(), SubMenuActivity.class);
        switch (view.getId()) {
            case R.id.culinary:
                intent.putExtra(SUB_MENU_TITLE, getResources().getString(R.string.culinary));
                startActivity(intent);
                break;
            case R.id.craft:
                intent.putExtra(SUB_MENU_TITLE, getResources().getString(R.string.craft));
                startActivity(intent);
                break;
            case R.id.events:
                intent.putExtra(SUB_MENU_TITLE, getResources().getString(R.string.events));
                startActivity(intent);
                break;
            case R.id.nativeLanguage:
                intent.putExtra(SUB_MENU_TITLE, getResources().getString(R.string.native_language));
                startActivity(intent);
                break;

        }
    }

}
