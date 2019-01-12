package com.zulfakarelzaf.authenticjogja.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;
import com.zulfakarelzaf.authenticjogja.R;
import com.zulfakarelzaf.authenticjogja.activity.DeveloperActivity;
import com.zulfakarelzaf.authenticjogja.activity.FotoSource;
import com.zulfakarelzaf.authenticjogja.util.PrefManager;

import io.paperdb.Paper;

import static com.zulfakarelzaf.authenticjogja.util.PrefManager.BOTTOM_POS_AFTER_CHANGING_LANGUAGE;

public class More extends Fragment implements View.OnClickListener {
    CheckBox cbSwitch;
    CheckBox cbPush;
    Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_more, container, false);

        view.findViewById(R.id.rvSwitchLang).setOnClickListener(this);
        view.findViewById(R.id.rvPushNotif).setOnClickListener(this);
        view.findViewById(R.id.tvFeedback).setOnClickListener(this);
        view.findViewById(R.id.tvRate).setOnClickListener(this);
        view.findViewById(R.id.tvShare).setOnClickListener(this);
        view.findViewById(R.id.tvPhotoSource).setOnClickListener(this);
        view.findViewById(R.id.tvTeam).setOnClickListener(this);
        mContext = getActivity().getBaseContext();
        Paper.init(mContext);
        cbPush = view.findViewById(R.id.cbPushNotif);
        cbSwitch = view.findViewById(R.id.cbSwitchLang);
        cbSwitch.setOnClickListener(this);
        cbSwitch.setChecked(Paper.book().read("language").equals("in"));

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cbSwitchLang:
                if (cbSwitch.isChecked()) {
                    Paper.book().write("language", "in");
                    cbSwitch.setChecked(true);
                } else {
                    Paper.book().write("language", "en");
                    cbSwitch.setChecked(false);
                }
                PrefManager prefManager = new PrefManager(getActivity().getApplicationContext());
                prefManager.setInt(BOTTOM_POS_AFTER_CHANGING_LANGUAGE, 3);
                getActivity().recreate();
                break;
            case R.id.rvPushNotif:
                if (cbPush.isChecked()) {
                    cbPush.setChecked(false);
                } else {
                    cbPush.setChecked(true);
                }
                break;
            case R.id.tvFeedback:
                Toast.makeText(getActivity().getBaseContext(),
                        getActivity().getResources().getString(R.string.feedback),
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.tvRate:
                Toast.makeText(getActivity().getBaseContext(),
                        getActivity().getResources().getString(R.string.rate_our_app),
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.tvShare:
                Toast.makeText(getActivity().getBaseContext(),
                        getActivity().getResources().getString(R.string.share_our_app),
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.tvPhotoSource:
                startActivity(new Intent(getContext(), FotoSource.class));
                break;
            case R.id.tvTeam:
                startActivity(new Intent(getContext(), DeveloperActivity.class));
                break;
        }
    }
}