package com.zulfakarelzaf.authenticjogja.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zulfakarelzaf.authenticjogja.R;

import java.util.ArrayList;

public class TriviaAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<String> triviaData;

    public TriviaAdapter(@NonNull Context context, ArrayList<String> triviaData) {
        super(context, R.layout.trivia_item, triviaData);
        this.context = context;
        this.triviaData = triviaData;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.trivia_item, parent, false);
        TextView trivia = view.findViewById(R.id.text1);
        trivia.setText(triviaData.get(position));
        return view;
    }
}