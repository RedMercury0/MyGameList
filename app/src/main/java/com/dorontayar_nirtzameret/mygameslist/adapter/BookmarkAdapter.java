package com.dorontayar_nirtzameret.mygameslist.adapter;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dorontayar_nirtzameret.mygameslist.R;
import com.dorontayar_nirtzameret.mygameslist.model.bookmarksModel.BookmarkModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.ViewHolder> {

    private ArrayList<BookmarkModel> items;
    private OnClickAdapterListener itemClick;

    public BookmarkAdapter(OnClickAdapterListener itemClick) {
        this.itemClick = itemClick;
    }

    public interface OnClickAdapterListener {
        void onClick(BookmarkModel result);
        void onDoubleClick(BookmarkModel result); // New interface method for double click
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_bookmark, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookmarkModel model = items.get(position);
        holder.title.setText(model.getGame_name());
        holder.desc.setText(model.getDescription());

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
    public ArrayList<BookmarkModel> getItems() {
        return items;
    }
    public BookmarkModel getItem(int position) {
        return items != null && position >= 0 && position < items.size() ? items.get(position) : null;
    }
    public void setPosts(List<BookmarkModel> items) {
        this.items = new ArrayList<>(items);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {

        private ImageView photoGame;
        private TextView title, desc;
        private GestureDetector gestureDetector;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photoGame = itemView.findViewById(R.id.photoGame);
            title = itemView.findViewById(R.id.gameTitle);
            desc = itemView.findViewById(R.id.gameDesc);

            // Setup GestureDetector
            gestureDetector = new GestureDetector(itemView.getContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    // Handle double-click action here
                    itemClick.onDoubleClick(items.get(getAdapterPosition()));
                    return true;
                }
            });

            // Set OnTouchListener to detect touch events
            itemView.setOnTouchListener(this);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }
    }



}

