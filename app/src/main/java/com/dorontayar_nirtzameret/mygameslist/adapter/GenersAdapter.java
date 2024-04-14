package com.dorontayar_nirtzameret.mygameslist.adapter;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dorontayar_nirtzameret.mygameslist.R;
import com.dorontayar_nirtzameret.mygameslist.model.genresModel.GenresResult;

import java.util.ArrayList;
import java.util.List;

public class GenersAdapter extends RecyclerView.Adapter<GenersAdapter.ViewHolder> {

    private ArrayList<GenresResult> items;
    private OnClickAdapterListener itemClick;

    public GenersAdapter(GenersAdapter.OnClickAdapterListener itemClick) {this.itemClick = itemClick;}

    public interface OnClickAdapterListener {
        void onClick(GenresResult game, ArrayList<GenresResult> items);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public void setPosts(List<GenresResult> items) {
        this.items = new ArrayList<>(items);
        notifyDataSetChanged();
    }

    public ArrayList<GenresResult> getPost() {
        return items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filter_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final GenresResult model = items.get(position);
        holder.titleType.setText(model.getName());
        holder.titleBack.setBackgroundColor(model.isClicked() ? Color.parseColor("#FF6200EE") : Color.parseColor("#53575F"));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick.onClick(model, items);
                notifyDataSetChanged();
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View titleBack;
        TextView titleType;
        public ViewHolder(View view) {
            super(view);
            titleBack = view.findViewById(R.id.titleBack);
            titleType = view.findViewById(R.id.titleType);
        }
    }
}

