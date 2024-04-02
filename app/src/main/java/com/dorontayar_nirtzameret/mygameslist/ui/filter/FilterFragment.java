package com.dorontayar_nirtzameret.mygameslist.ui.filter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.dorontayar_nirtzameret.mygameslist.R;
import com.dorontayar_nirtzameret.mygameslist.adapter.FilterAdapter;
import com.dorontayar_nirtzameret.mygameslist.model.searchModel.Result;
import com.dorontayar_nirtzameret.mygameslist.model.searchModel.SearchModel;
import com.dorontayar_nirtzameret.mygameslist.network.ApiManager;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FilterFragment extends Fragment {
    private FilterAdapter filterAdapter;
    private BottomSheetDialog bottomSheetDialog;
    private RecyclerView recyclerView;
    private LottieAnimationView avFromCode;
    private TextView gameFind;
    String apiKey;

    public FilterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);

        apiKey = getContext().getString(R.string.RAWG_API_KEY);

        setupFilterRecyclerView();

        recyclerView = view.findViewById(R.id.filter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        initializeAdapters();
        Log.d(TAG, "Adapters initialized.");

        avFromCode = view.findViewById(R.id.av_from_code);
        gameFind = view.findViewById(R.id.gameFind);

        // Set OnClickListener for the search icon
        view.findViewById(R.id.filterOpen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBottomSheetDialog();
            }
        });


        return view;
    }

    private void setupFilterRecyclerView() {

    }

    private void initializeAdapters() {
        // Initialize FilterAdapter
        filterAdapter = new FilterAdapter(new FilterAdapter.OnClickAdapterListener() {
            @Override
            public void onClick(Result result) {
                // Handle item click event
            }
        });

        // Set adapter to RecyclerView
        recyclerView.setAdapter(filterAdapter);

        // Set RecyclerView visibility
        recyclerView.setVisibility(View.VISIBLE);

        // Initialize other adapters here, if needed
        // genersAdapter = new GenersAdapter();
        // platformAdapter = new PlatformAdapter();
    }

    // Method to open bottom sheet dialog for searching
    private void openBottomSheetDialog() {
        // Inflate the bottom sheet layout
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottomsheetdialog, null);

        // Create the bottom sheet dialog
        bottomSheetDialog = new BottomSheetDialog(requireContext());

        // Set the content view for the bottom sheet dialog
        bottomSheetDialog.setContentView(bottomSheetView);

        // Set OnClickListener for the find button
        bottomSheetView.findViewById(R.id.findButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Extract search parameters from the dialog
                EditText searchEditText = bottomSheetView.findViewById(R.id.textInsert);
                String searchText = searchEditText.getText().toString();

                // Perform search based on searchText
                performSearch(searchText);

                // Dismiss the dialog
                bottomSheetDialog.dismiss();
            }
        });

        // Show the bottom sheet dialog
        bottomSheetDialog.show();
    }


    // Method to perform search based on the entered text
    private void performSearch(String searchText) {
        // Make API call to search for games
        ApiManager.searchGames(getContext(), "25", searchText, 1, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<SearchModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        // Disposable
                    }

                    @Override
                    public void onSuccess(@NonNull SearchModel searchModel) {
                        // Update the RecyclerView with search results
                        filterAdapter.setPosts(searchModel.getResults());

                        // Hide the LottieAnimationView and TextView
                        hideGameFinder();

                        // Dismiss the dialog if necessary
                        if (bottomSheetDialog != null && bottomSheetDialog.isShowing()) {
                            bottomSheetDialog.dismiss();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        // Handle error
                        Log.e(TAG, "Search error: " + e.getMessage());
                    }
                });
    }

    private void hideGameFinder() {
        if (avFromCode != null) {
            avFromCode.setVisibility(View.INVISIBLE);
        }
        if (gameFind != null) {
            gameFind.setVisibility(View.INVISIBLE);
        }
    }

}

