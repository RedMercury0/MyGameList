package com.dorontayar_nirtzameret.mygameslist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dorontayar_nirtzameret.mygameslist.R;
import com.dorontayar_nirtzameret.mygameslist.model.popularModel.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder> {

    private ArrayList<Result> items;
    private OnClickAdapterListner itemClick;

    public TopAdapter(OnClickAdapterListner itemClick) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result model = items.get(position);
        holder.title.setText(model.getName());
        holder.type.setText(String.valueOf(model.getRating()));
        holder.itemView.setOnClickListener(v -> {
            if (itemClick != null) {
                itemClick.onClick(model);
            }
        });

        Picasso.get()
                .load(model.getBackground_image())
                .resize(900, 500)
                .centerCrop()
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

        TextView title;
        TextView type;
        ImageView photoGame;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            type = itemView.findViewById(R.id.type);
            photoGame = itemView.findViewById(R.id.photoGame);
        }
    }
}

