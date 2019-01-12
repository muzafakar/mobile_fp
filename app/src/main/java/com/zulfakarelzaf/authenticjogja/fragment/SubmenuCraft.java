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
import android.widget.ScrollView;

import com.zulfakarelzaf.authenticjogja.adapter.SubMenuAdapter;
import com.zulfakarelzaf.authenticjogja.model.Craft;
import com.zulfakarelzaf.authenticjogja.presenter.Contract;
import com.zulfakarelzaf.authenticjogja.presenter.SubMenuPresenter;
import com.zulfakarelzaf.authenticjogja.R;

import java.util.ArrayList;

public class SubmenuCraft extends Fragment implements Contract.View{



    private RecyclerView rcEvent;
    private SubMenuAdapter adapter;
    private ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        SubMenuPresenter presenter = new SubMenuPresenter(this);
        View view =  inflater.inflate(R.layout.fragment_submenu,container,false);
//        ImageView img = view.findViewById(R.id.craft_batik);
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getContext(), ListCulinaryActivity.class));
//            }
//        });

        progressBar = view.findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);

        rcEvent = view.findViewById(R.id.rv_eventfragment);
        rcEvent.setLayoutManager(new LinearLayoutManager(getContext()));
        if(adapter != null){
            rcEvent.setAdapter(adapter);
            progressBar.setVisibility(View.INVISIBLE);
        }else {
            presenter.getCraft();
        }

        return view;
    }


    @Override
    public void onCompleteCall(ArrayList data) {
        adapter= new SubMenuAdapter<Craft>(getContext(),data);
        rcEvent.setAdapter(adapter);
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void setData(ArrayList data){
        adapter = new SubMenuAdapter(getContext(),data);
        rcEvent.setAdapter(adapter);
    }
}
