package com.dorontayar_nirtzameret.mygameslist.main.bookmarksFragment;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dorontayar_nirtzameret.mygameslist.R;
import com.dorontayar_nirtzameret.mygameslist.adapter.BookmarkAdapter;
import com.dorontayar_nirtzameret.mygameslist.main.previewActivity.PreviewGameActivity;
import com.dorontayar_nirtzameret.mygameslist.model.bookmarksModel.BookmarkModel;
import com.dorontayar_nirtzameret.mygameslist.model.detailModel.InfoGame;
import com.dorontayar_nirtzameret.mygameslist.network.ApiManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class BookmarksFragment extends Fragment {

    private DatabaseReference databaseReference;
    private String user;
    private RecyclerView recyclerView;
    private BookmarkAdapter bookmarkAdapter;
    private List<BookmarkModel> bookmarkList = new ArrayList<>();
    private String apiKey;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookmarks, container, false);

        apiKey = getContext().getString(R.string.RAWG_API_KEY);

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mygamelist-androidproject-default-rtdb.firebaseio.com/");

        SharedPreferences prefs = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        user = prefs.getString("loggedInUser", null);

        recyclerView = view.findViewById(R.id.myGamesRec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        initializeAdapters();
        getBookmarks();
        attachSwipeToDelete();

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        getBookmarks();
    }

    private void initializeAdapters() {
        bookmarkAdapter = new BookmarkAdapter(new BookmarkAdapter.OnClickAdapterListener() {
            @Override
            public void onClick(BookmarkModel bookmarkModel) {
                fetchDetail(bookmarkModel.getSlug());
            }

            @Override
            public void onDoubleClick(BookmarkModel bookmarkModel) {}

        });


        recyclerView.setAdapter(bookmarkAdapter);
        recyclerView.setVisibility(View.VISIBLE);

    }

    private void openPreviewActivity(InfoGame infoGame) {
        Intent intent = new Intent(getContext(), PreviewGameActivity.class);

        String infoGameJson = new Gson().toJson(infoGame);
        intent.putExtra("infoGame",infoGame.getName());
        intent.putExtra("infoGameJson", infoGameJson);

        startActivity(intent);
    }
    private void fetchDetail(String gameName) {
        ApiManager.getGameInfo(getContext(),gameName, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<InfoGame>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onSuccess(@NonNull InfoGame infoGame) {
                        openPreviewActivity(infoGame);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "Failed to fetch platforms: " + e.getMessage());
                    }
                });
    }
    private void getBookmarks() {
        DatabaseReference userBookmarksRef = FirebaseDatabase.getInstance().getReference("users").child(user).child("bookmarks");
        userBookmarksRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                bookmarkList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    BookmarkModel bookmarkModel = snapshot.getValue(BookmarkModel.class);
                    bookmarkList.add(bookmarkModel);
                }

                bookmarkAdapter.setPosts(bookmarkList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    // Handle the swipe-to-delete bookmark logic
    private void attachSwipeToDelete() {
        ItemTouchHelper.SimpleCallback swipeToDeleteCallback = new ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                BookmarkModel bookmarkModel = bookmarkAdapter.getItems().get(position);
                removeBookmark(bookmarkModel.getGame_id());
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                getDefaultUIUtil().onDraw(c, recyclerView, viewHolder.itemView, dX, dY, actionState, isCurrentlyActive);
            }

            @Override
            public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                getDefaultUIUtil().onDrawOver(c, recyclerView, viewHolder.itemView, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void removeBookmark(String gameId) {
        DatabaseReference userBookmarksRef = databaseReference.child("users").child(user).child("bookmarks").child(gameId);

        userBookmarksRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    removeBookmarkFromLocalList(gameId);
                } else {
                    Log.w("Bookmarks","Failed to remove bookmark");
                }
            }
        });
    }

    private void removeBookmarkFromLocalList(String gameId) {
        for (int i = 0; i < bookmarkAdapter.getItemCount(); i++) {
            BookmarkModel bookmark = bookmarkAdapter.getItem(i);
            if (bookmark.getGame_id().equals(gameId)) {
                bookmarkAdapter.getItems().remove(i);
                bookmarkAdapter.notifyItemRemoved(i);
                break;
            }
        }
    }
}