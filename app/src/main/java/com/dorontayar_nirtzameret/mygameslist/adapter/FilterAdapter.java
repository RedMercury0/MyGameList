package com.dorontayar_nirtzameret.mygameslist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dorontayar_nirtzameret.mygameslist.R;
import com.dorontayar_nirtzameret.mygameslist.model.commonGameModel.Genre;
import com.dorontayar_nirtzameret.mygameslist.model.commonGameModel.ParentPlatform;
import com.dorontayar_nirtzameret.mygameslist.model.searchModel.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {

    private ArrayList<Result> items;
    private OnClickAdapterListener itemClick;

    public FilterAdapter(OnClickAdapterListener itemClick) {
        this.itemClick = itemClick;
    }

    public interface OnClickAdapterListener {
        void onClick(Result result);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game_filter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result model = items.get(position);
        holder.title.setText(model.getName());
        holder.rateGameToolbar.setText(String.valueOf(model.getRating()));


        StringBuilder platform = new StringBuilder();
        for (ParentPlatform platformItem : model.getParent_platforms()) {
            platform.append(platformItem.getPlatform().getName()).append(", ");
        }
        if (platform.length() > 2) {
            platform.setLength(platform.length() - 2);
        }
        StringBuilder genres = new StringBuilder();
        for (Genre genre : model.getGenres()) {
            genres.append(genre.getName()).append(", ");
        }
        if (genres.length() > 2) {
            genres.setLength(genres.length() - 2);
        }

        holder.desc.setText(platform.toString());
        holder.desc2.setText(genres.toString());

        holder.title.setSelected(true);
        holder.desc.setSelected(true);

        Picasso.get()
                .load(model.getBackground_image())
                .resize(750, 500)
                //Todo add error loading
                .into(holder.photoGame);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.onClick(model);
            }
        });
    }


    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public void setPosts(List<Result> items) {
        this.items = new ArrayList<>(items);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView photoGame;
        TextView title, rateGameToolbar, desc,desc2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photoGame = itemView.findViewById(R.id.photoGame);
            title = itemView.findViewById(R.id.title);
            rateGameToolbar = itemView.findViewById(R.id.rateGameToolbar);
            desc = itemView.findViewById(R.id.desc);
            desc2 = itemView.findViewById(R.id.desc2);
        }
    }
}
