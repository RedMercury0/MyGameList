package com.dorontayar_nirtzameret.mygameslist.adapter;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dorontayar_nirtzameret.mygameslist.R;
import com.dorontayar_nirtzameret.mygameslist.model.platformModel.PlatformResult;

import java.util.ArrayList;
import java.util.List;

public class PlatformAdapter extends RecyclerView.Adapter<PlatformAdapter.ViewHolder> {

    private ArrayList<PlatformResult> items;
    private OnClickAdapterListener itemClick;

    public PlatformAdapter(PlatformAdapter.OnClickAdapterListener itemClick) {this.itemClick = itemClick;}

    public interface OnClickAdapterListener {
        void onClick(PlatformResult game, ArrayList<PlatformResult> items);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public void setPosts(List<PlatformResult> items) {
        this.items = new ArrayList<>(items);
        notifyDataSetChanged();
    }

    public ArrayList<PlatformResult> getPost() {
        return this.items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filter_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PlatformResult model = items.get(position);
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
        RelativeLayout titleBack;
        TextView titleType;

        public ViewHolder(View view) {
            super(view);
            titleBack = view.findViewById(R.id.titleBack);
            titleType = view.findViewById(R.id.titleType);
        }
    }

}
