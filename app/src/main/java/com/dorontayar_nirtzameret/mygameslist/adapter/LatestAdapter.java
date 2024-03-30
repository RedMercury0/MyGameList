package com.dorontayar_nirtzameret.mygameslist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dorontayar_nirtzameret.mygameslist.R;
import com.dorontayar_nirtzameret.mygameslist.model.popularModel.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LatestAdapter extends RecyclerView.Adapter<LatestAdapter.ViewHolder> {

    private ArrayList<Result> items;
    private OnClickAdapterListner itemClick;

    public LatestAdapter(OnClickAdapterListner itemClick) {
        this.itemClick = itemClick;
        items = new ArrayList<>();
    }

    public void setPosts(List<Result> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_latest, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result model = items.get(position);
        holder.itemView.setOnClickListener(v -> {
            if (itemClick != null) {
                itemClick.onClick(model);
            }
        });
        Picasso.get()
                .load(model.getBackground_image())
                .resize(400, 300)
                .into(holder.photoGame);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnClickAdapterListner {
        void onClick(Result game);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView photoGame;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            photoGame = itemView.findViewById(R.id.photoGame);
        }
    }
}
