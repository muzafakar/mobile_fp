package com.zulfakarelzaf.authenticjogja.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zulfakarelzaf.authenticjogja.activity.CulinariesDetailActivity;
import com.zulfakarelzaf.authenticjogja.activity.LanguageDetailActivity;
import com.zulfakarelzaf.authenticjogja.activity.MenuDetailActivity;
import com.zulfakarelzaf.authenticjogja.model.CategoryFood;
import com.zulfakarelzaf.authenticjogja.model.CategoryLanguages;
import com.zulfakarelzaf.authenticjogja.model.Culinaries;
import com.zulfakarelzaf.authenticjogja.model.ItemModel;
import com.zulfakarelzaf.authenticjogja.R;

import java.util.List;


/**
 * Created by zulfakar on 22/05/18.
 * For Educational and Personal purposes
 */

public class MenuWithThumbnailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List data;

    public MenuWithThumbnailAdapter(Context context, List data) {
        this.context = context;
        this.data = data;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.menu_list_item, parent, false);

        return new MenutItemClass(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {


        if (!data.isEmpty()) {
            if (data.get(position) instanceof CategoryFood) {
                CategoryFood categoryFood = (CategoryFood) data.get(position);
                ((MenutItemClass) holder).tvTitle.setText(categoryFood.getName());
                Picasso.get().load(categoryFood.getImageUrl().getUrl())
                        .fit()
                        .centerCrop()
                        .into(((MenutItemClass) holder).imgItem);

                ((MenutItemClass) holder).menuItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, CulinariesDetailActivity.class);
                        intent.putExtra("data", (CategoryFood) data.get(position));
                        context.startActivity(intent);
                    }
                });
            }

            if (data.get(0) instanceof Culinaries) {
                Picasso.get().load(((Culinaries) data.get(position)).getImageUrl().getUrl()).into(((MenutItemClass) holder).imgItem);
                ((MenutItemClass) holder).tvDetail.setText("");
                ((MenutItemClass) holder).tvTitle.setText(((Culinaries) data.get(position)).getName());
                ((MenutItemClass) holder).menuItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, MenuDetailActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("data", (Culinaries) data.get(position));
                        context.startActivity(intent);
                    }
                });
            }

            if (data.get(0) instanceof CategoryLanguages) {
                ((MenutItemClass) holder).tvTitle.setText(((CategoryLanguages) data.get(position)).getName());
                ((MenutItemClass) holder).menuItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, LanguageDetailActivity.class);
                        intent.putExtra("data", (CategoryLanguages) data.get(position));
                        context.startActivity(intent);
                    }
                });
            }

        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<ItemModel> data) {
        this.data = data;
    }

    class MenutItemClass extends RecyclerView.ViewHolder {
        ImageView imgItem;
        TextView tvTitle;
        TextView tvDetail;
        ConstraintLayout menuItem;

        public MenutItemClass(View itemView) {
            super(itemView);
            tvDetail = itemView.findViewById(R.id.tvMenuDetail);
            imgItem = itemView.findViewById(R.id.imgMenu);
            tvTitle = itemView.findViewById(R.id.tvMenuTitle);
            menuItem = itemView.findViewById(R.id.menuItem);


        }
    }
}
