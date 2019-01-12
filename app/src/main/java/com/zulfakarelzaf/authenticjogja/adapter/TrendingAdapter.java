package com.zulfakarelzaf.authenticjogja.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zulfakarelzaf.authenticjogja.R;

import java.util.List;

/**
 * Created by XAgusart on 5/21/2018.
 */

public class TrendingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Integer> trendingImage;
    private List<String> trendingTitle;
    private List<String> trendingSubTitle;

    public TrendingAdapter(Context context, List<Integer> trendingImage, List<String> trendingTitle, List<String> trendingSubTitle) {
        this.context = context;
        this.trendingImage = trendingImage;
        this.trendingTitle = trendingTitle;
        this.trendingSubTitle = trendingSubTitle;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trending_item, parent, false);
        return new TrendingItem(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((TrendingItem) holder).imgTrending.setImageResource(trendingImage.get(position));
        ((TrendingItem) holder).tvTrendingTitle.setText(trendingTitle.get(position));
        ((TrendingItem) holder).tvTrendingDate.setText(trendingSubTitle.get(position));
        ((TrendingItem) holder).trendingItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "To item: " + trendingTitle.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return trendingImage.size();
    }

    class TrendingItem extends RecyclerView.ViewHolder {
        ConstraintLayout trendingItem;
        ImageView imgTrending;
        TextView tvTrendingTitle;
        TextView tvTrendingDate;

        public TrendingItem(View itemView) {
            super(itemView);
            imgTrending = itemView.findViewById(R.id.imgTrending);
            tvTrendingTitle = itemView.findViewById(R.id.judulTrending);
            tvTrendingDate = itemView.findViewById(R.id.tanggalTrending);
            trendingItem = itemView.findViewById(R.id.trendingItem);
        }
    }
}