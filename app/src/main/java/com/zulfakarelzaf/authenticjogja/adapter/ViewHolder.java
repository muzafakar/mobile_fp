package com.zulfakarelzaf.authenticjogja.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zulfakarelzaf.authenticjogja.R;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private OnItemClickListener listener;
    ImageView imgItem;
    TextView tvTitle;
    TextView tvDetail;
    private View menuItem;
    public ViewHolder(View itemView) {
        super(itemView);
        imgItem = itemView.findViewById(R.id.imgMenu);
        tvTitle = itemView.findViewById(R.id.tvMenuTitle);
        menuItem = itemView.findViewById(R.id.menuItem);
        tvDetail = itemView.findViewById(R.id.tvMenuDetail);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view,getAdapterPosition());
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
