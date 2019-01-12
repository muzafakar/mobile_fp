package com.zulfakarelzaf.authenticjogja.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


import com.zulfakarelzaf.authenticjogja.adapter.SubMenuAdapter;
import com.zulfakarelzaf.authenticjogja.presenter.Contract;
import com.zulfakarelzaf.authenticjogja.presenter.SubMenuPresenter;
import com.zulfakarelzaf.authenticjogja.R;

import java.util.ArrayList;
import java.util.List;


public class SubmenuEvent extends Fragment implements Contract.View{

    private RecyclerView rcEvent;
    private SubMenuAdapter adapter;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SubMenuPresenter presenter = new SubMenuPresenter(this);
        View view =  inflater.inflate(R.layout.fragment_submenu,container,false);
        rcEvent = view.findViewById(R.id.rv_eventfragment);
        rcEvent.setLayoutManager(new LinearLayoutManager(getContext()));
        progressBar = view.findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        if(adapter != null){
            rcEvent.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);

        }else {
            presenter.getEvent();
        }
        return view;
    }


    @Override
    public void onCompleteCall(ArrayList data) {
        adapter = new SubMenuAdapter(getContext(),data);
        rcEvent.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
    }

    public void setData(ArrayList data){
        adapter = new SubMenuAdapter(getContext(),data);
        rcEvent.setAdapter(adapter);

    }

}
