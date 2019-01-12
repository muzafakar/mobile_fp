package com.zulfakarelzaf.authenticjogja.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.zulfakarelzaf.authenticjogja.activity.LanguageDetailActivity;
import com.zulfakarelzaf.authenticjogja.activity.MenuDetailActivity;
import com.zulfakarelzaf.authenticjogja.activity.CulinariesDetailActivity;
import com.zulfakarelzaf.authenticjogja.activity.SubMenuActivity;
import com.zulfakarelzaf.authenticjogja.common.Common;
import com.zulfakarelzaf.authenticjogja.model.CategoryFood;
import com.zulfakarelzaf.authenticjogja.model.CategoryLanguages;
import com.zulfakarelzaf.authenticjogja.model.Craft;
import com.zulfakarelzaf.authenticjogja.model.Culinaries;
import com.zulfakarelzaf.authenticjogja.model.Event;
import com.zulfakarelzaf.authenticjogja.R;
import com.zulfakarelzaf.authenticjogja.model.ImageUrl;

import java.io.Serializable;
import java.util.List;


public class SubMenuAdapter<T extends Serializable> extends RecyclerView.Adapter<ViewHolder> {
    private List<T> data;
    private Context context;

    public SubMenuAdapter(Context context, List<T> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.menu_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final T tmp = data.get(position);
        if (tmp instanceof Event) {
            ImageUrl imageUrl = ((Event) tmp).getImage();
            Picasso.get().load(imageUrl.getUrl()).fit().centerCrop().into(holder.imgItem);
            holder.tvTitle.setText(((Event) data.get(position)).getName());
            holder.tvDetail.setText(((Event) data.get(position)).getDate());
        }
        if (tmp instanceof Craft) {
            holder.tvTitle.setText(((Craft) data.get(position)).getName());
            Picasso.get().load(((Craft) tmp).getImage().getUrl())
                    .fit()
                    .centerCrop()
                    .into(holder.imgItem);
            holder.tvDetail.setText("");
        }

        if (tmp instanceof Culinaries) {
            holder.tvTitle.setText(((Culinaries) data.get(position)).getName());
            holder.tvDetail.setText("");
            ImageUrl imageUrl = ((Culinaries) tmp).getImageUrl();
            Picasso.get().load(imageUrl.getUrl()).fit().centerCrop().into(holder.imgItem);
            holder.setListener(new OnItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Intent intent = new Intent(context, MenuDetailActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("data", tmp);
                    context.startActivity(intent);
                }
            });
            return;

        }
        if (tmp instanceof CategoryFood) {
            holder.tvTitle.setText(((CategoryFood) data.get(position)).getName());
            holder.tvDetail.setText("");
            Picasso.get().load(((CategoryFood) tmp).getImageUrl().getUrl())
                    .fit()
                    .centerCrop()
                    .into(holder.imgItem);

            holder.setListener(new OnItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Intent intent = new Intent(context, CulinariesDetailActivity.class);
                    intent.putExtra("categoryId", ((CategoryFood) tmp).getId());
                    intent.putExtra("data", tmp);
                    context.startActivity(intent);
                }
            });
            return;
        }

        if (tmp instanceof CategoryLanguages) {
            holder.tvTitle.setText(((CategoryLanguages) tmp).getName());
            holder.tvDetail.setText("");
            int id = ((CategoryLanguages) tmp).getId()-1;
            Picasso.get().load(Common.getLanguagesUrl().get(id)).into(holder.imgItem);
            holder.setListener(new OnItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Intent intent = new Intent(context, LanguageDetailActivity.class);
                    intent.putExtra("data", tmp);
                    context.startActivity(intent);
                }
            });
            return;
        }


        holder.setListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(context, MenuDetailActivity.class);
//                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("data", data.get(position));
//                intent.setPackage(MenuDetailActivity.ACTIVITY_SERVICE);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
