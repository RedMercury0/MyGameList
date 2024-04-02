package com.dorontayar_nirtzameret.mygameslist.adapter;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dorontayar_nirtzameret.mygameslist.R;
import com.dorontayar_nirtzameret.mygameslist.model.genresModel.Result;

import java.util.ArrayList;
import java.util.List;

public class GenersAdapter extends RecyclerView.Adapter<GenersAdapter.ViewHolder> {

    private ArrayList<Result> items;
    private OnClickAdapterListner itemClick;

    // Interface for item click listener
    public interface OnClickAdapterListner {
        void onClick(Result game, ArrayList<Result> items);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    // Setter for the list of items
    public void setPosts(List<Result> items) {
        this.items = new ArrayList<>(items);
        notifyDataSetChanged();
    }

    // Getter for the list of items
    public ArrayList<Result> getPost() {
        return items;
    }

    // Inflates the item views
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_geners, parent, false);
        return new ViewHolder(view);
    }

    // Binds each item in the ArrayList to a view
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Result model = items.get(position);
        holder.titleType.setText(model.getName());
        holder.titleBack.setBackgroundColor(model.isClicked() ? Color.parseColor("#c43e00") : Color.parseColor("#342A24"));

        // Set click listener for the item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick.onClick(model, items);
                notifyDataSetChanged();
            }
        });
    }

    // ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Holds the TextView that will add each picture to
        View titleBack;
        TextView titleType;
        public ViewHolder(View view) {
            super(view);
            titleBack = view.findViewById(R.id.titleBack);
            titleType = view.findViewById(R.id.titleType);
        }
    }
}

