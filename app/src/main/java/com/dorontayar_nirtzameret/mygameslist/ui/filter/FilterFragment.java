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
import com.dorontayar_nirtzameret.mygameslist.adapter.GenersAdapter;
import com.dorontayar_nirtzameret.mygameslist.adapter.PlatformAdapter;
import com.dorontayar_nirtzameret.mygameslist.model.genresModel.GenresModel;
import com.dorontayar_nirtzameret.mygameslist.model.platformModel.PlatformModel;
import com.dorontayar_nirtzameret.mygameslist.model.searchModel.Result;
import com.dorontayar_nirtzameret.mygameslist.model.searchModel.SearchModel;
import com.dorontayar_nirtzameret.mygameslist.network.ApiManager;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FilterFragment extends Fragment {
    private FilterAdapter filterAdapter;
    private GenersAdapter genersAdapter;
    private PlatformAdapter platformAdapter;
    private BottomSheetDialog bottomSheetDialog;
    private RecyclerView recyclerView;
    private LottieAnimationView avFromCode;
    private TextView gameFind;
    String apiKey;
    String selectedGenres = "";
    String selectedPlatforms = "";

    public FilterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);

        apiKey = getContext().getString(R.string.RAWG_API_KEY);


        // Fetch genres and platforms data from API
        fetchGenres();
        fetchPlatforms();

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



    private void initializeAdapters() {
        // Initialize FilterAdapter
        filterAdapter = new FilterAdapter(new FilterAdapter.OnClickAdapterListener() {
            @Override
            public void onClick(Result result) {
                // Handle item click event
            }
        });
        // Initialize GenersAdapter with item click listener
        genersAdapter = new GenersAdapter(new GenersAdapter.OnClickAdapterListener() {
            @Override
            public void onClick(com.dorontayar_nirtzameret.mygameslist.model.genresModel.Result item, ArrayList<com.dorontayar_nirtzameret.mygameslist.model.genresModel.Result> items) {
                // Handle item click event for genres
                item.setClicked(!item.isClicked());
                // Notify the adapter of the dataset change
                genersAdapter.notifyDataSetChanged();
                // You can also update the selectedGenres variable here based on the clicked item
            }
        });

        // Initialize PlatformAdapter
        platformAdapter = new PlatformAdapter(new PlatformAdapter.OnClickAdapterListener() {
            @Override
            public void onClick(com.dorontayar_nirtzameret.mygameslist.model.platformModel.Result item, ArrayList<com.dorontayar_nirtzameret.mygameslist.model.platformModel.Result> items) {
                // Handle item click event for genres
                item.setClicked(!item.isClicked());
                // Notify the adapter of the dataset change
                platformAdapter.notifyDataSetChanged();
                // You can also update the selectedGenres variable here based on the clicked item
            }
        });

        // Set adapter to RecyclerView
        recyclerView.setAdapter(filterAdapter);

        // Set RecyclerView visibility
        recyclerView.setVisibility(View.VISIBLE);


    }

    // Method to open bottom sheet dialog for searching
    private void openBottomSheetDialog() {
        // Inflate the bottom sheet layout
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottomsheetdialog, null);

        // Create the bottom sheet dialog
        bottomSheetDialog = new BottomSheetDialog(requireContext());

        // Set the content view for the bottom sheet dialog
        bottomSheetDialog.setContentView(bottomSheetView);


        // Get the RecyclerViews for genres and platforms
        RecyclerView genresRecyclerView = bottomSheetView.findViewById(R.id.genresRec);
        RecyclerView platformsRecyclerView = bottomSheetView.findViewById(R.id.platformRec);

        // Set LayoutManager for RecyclerViews
        genresRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        platformsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));

        // Set adapters to RecyclerViews
        genresRecyclerView.setAdapter(genersAdapter);
        platformsRecyclerView.setAdapter(platformAdapter);


        // Set OnClickListener for the find button
        bottomSheetView.findViewById(R.id.findButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Extract search parameters from the dialog
                EditText searchEditText = bottomSheetView.findViewById(R.id.textInsert);
                String searchText = searchEditText.getText().toString();

                // Perform search based on searchText
                performSearch(searchText, 1);

                // Dismiss the dialog
                bottomSheetDialog.dismiss();
            }
        });

        // Show the bottom sheet dialog
        bottomSheetDialog.show();
    }


    // Method to fetch genres data from API
    private void fetchGenres() {
        // Make API call to get genres
        ApiManager.getGenres(getContext(), apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<GenresModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        // Disposable
                    }

                    @Override
                    public void onSuccess(@NonNull GenresModel genresModel) {
                        // Update GenersAdapter with genres data
                        genersAdapter.setPosts(genresModel.getResults());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        // Handle error
                        Log.e(TAG, "Failed to fetch genres: " + e.getMessage());
                    }
                });
    }

    // Method to fetch platforms data from API
    private void fetchPlatforms() {
        // Make API call to get platforms
        ApiManager.getPlatforms(getContext(), apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<PlatformModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        // Disposable
                    }

                    @Override
                    public void onSuccess(@NonNull PlatformModel platformModel) {
                        Log.e("platformLOG", "fetch platforms ");
                        // Update PlatformAdapter with platforms data
                        platformAdapter.setPosts(platformModel.getResults());
                        List<com.dorontayar_nirtzameret.mygameslist.model.platformModel.Result> listResultsPlat =platformModel.getResults();
                        Log.e("platformLOG", listResultsPlat.get(1).getName());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        // Handle error
                        Log.e(TAG, "Failed to fetch platforms: " + e.getMessage());
                    }
                });
    }


    // Method to perform search based on the entered text, selected genres, and selected platforms
    private void performSearch(String searchText,int page) {
        // Make API call to search for games
        //ApiManager.searchGamesByPlatformAndGenre(getContext(), "25", searchText, selectedPlatforms, selectedGenres, page, apiKey)
        ApiManager.searchGames(getContext(), "25", searchText,page, apiKey)

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


