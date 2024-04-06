package com.dorontayar_nirtzameret.mygameslist.main.ui.filter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
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
import com.dorontayar_nirtzameret.mygameslist.main.previewActivity.PreviewGameActivity;
import com.dorontayar_nirtzameret.mygameslist.model.detailModel.InfoGame;
import com.dorontayar_nirtzameret.mygameslist.model.genresModel.GenresModel;
import com.dorontayar_nirtzameret.mygameslist.model.genresModel.GenresResult;
import com.dorontayar_nirtzameret.mygameslist.model.platformModel.PlatformModel;
import com.dorontayar_nirtzameret.mygameslist.model.platformModel.PlatformResult;
import com.dorontayar_nirtzameret.mygameslist.model.searchModel.Result;
import com.dorontayar_nirtzameret.mygameslist.model.searchModel.SearchModel;
import com.dorontayar_nirtzameret.mygameslist.network.ApiManager;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;

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
    private String apiKey;
    private List<GenresResult> selectedGenres = new ArrayList<>();
    private List<PlatformResult> selectedPlatforms = new ArrayList<>();

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
                fetchDetail(result.getSlug());
            }
        });
        // Initialize GenresAdapter with item click listener
        genersAdapter = new GenersAdapter(new GenersAdapter.OnClickAdapterListener() {
            @Override
            public void onClick(GenresResult item, ArrayList<GenresResult> items) {
                // Handle item click event for genres
                item.setClicked(!item.isClicked());
                onGenreClicked(item);
                // Notify the adapter of the dataset change
                genersAdapter.notifyDataSetChanged();
            }
        });

        // Initialize PlatformAdapter
        platformAdapter = new PlatformAdapter(new PlatformAdapter.OnClickAdapterListener() {
            @Override
            public void onClick(PlatformResult item, ArrayList<PlatformResult> items) {
                // Handle item click event for platform
                item.setClicked(!item.isClicked());
                onPlatformClicked(item);
                // Notify the adapter of the dataset change
                platformAdapter.notifyDataSetChanged();
            }
        });

        // Set adapter to RecyclerView
        recyclerView.setAdapter(filterAdapter);

        // Set RecyclerView visibility
        recyclerView.setVisibility(View.VISIBLE);


    }

    // Open the bottom sheet dialog for searching
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

                // Perform search based on searchText and filters
                if (!searchText.isEmpty()) {
                    performSearch(searchText, 1);
                }

                // Dismiss the dialog
                bottomSheetDialog.dismiss();
            }
        });

        // Show the bottom sheet dialog
        bottomSheetDialog.show();
    }


    private void openPreviewActivity(InfoGame infoGame) {
        // Start the PreviewActivity and pass necessary data
        Intent intent = new Intent(getContext(), PreviewGameActivity.class);
        // Serialize InfoGame object into JSON string
        String infoGameJson = new Gson().toJson(infoGame);
        intent.putExtra("infoGame",infoGame.getName());
        intent.putExtra("infoGameJson", infoGameJson);
        startActivity(intent);
    }
    // Fetch genres data from API
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

    // Fetch platforms data from API
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
                        List<PlatformResult> listResultsPlat =platformModel.getResults();
                        Log.e("platformLOG", listResultsPlat.get(1).getName());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        // Handle error
                        Log.e(TAG, "Failed to fetch platforms: " + e.getMessage());
                    }
                });
    }

    // Fetch detailed game data from API
    private void fetchDetail(String gameName) {
        // Make API call to get game details
        ApiManager.getGameInfo(getContext(),gameName, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<InfoGame>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        // Disposable
                    }

                    @Override
                    public void onSuccess(@NonNull InfoGame infoGame) {
                        Log.e("InfoGameLOG", "fetch InfoGame ");
                        // Open the Prewview Game Activity with the selected game details
                        openPreviewActivity(infoGame);
                        Log.e("InfoGameLOG", infoGame.getName());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        // Handle error
                        Log.e(TAG, "Failed to fetch platforms: " + e.getMessage());
                    }
                });
    }

    // Perform search based on the entered text, selected genres, and selected platforms
    private void performSearch(String searchText, int page) {
        // Make API call to search for games

        if (selectedGenres.isEmpty() && selectedPlatforms.isEmpty()){
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

                        // Clear filtering selection
                        clearSelectedFilters();

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
        else if (selectedGenres.isEmpty() && !selectedPlatforms.isEmpty()){

            ApiManager.searchGamesByPlatform(getContext(), "25", searchText, getPlatformsId(), page, apiKey)

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

                            // Clear filtering selection
                            clearSelectedFilters();

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
        } else if (!selectedGenres.isEmpty() && selectedPlatforms.isEmpty()) {
            ApiManager.searchGamesByGenre(getContext(), "25", searchText, getGenresId(), page, apiKey)

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

                            // Clear filtering selection
                            clearSelectedFilters();

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
            }else {
            ApiManager.searchGamesByFilters(getContext(), "25", searchText, getPlatformsId(),getGenresId(), page, apiKey)

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

                            // Clear filtering selection
                            clearSelectedFilters();

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


    }

    private void hideGameFinder() {
        if (avFromCode != null) {
            avFromCode.setVisibility(View.INVISIBLE);
        }
        if (gameFind != null) {
            gameFind.setVisibility(View.INVISIBLE);
        }
    }

    // Handle item click in GenresAdapter and PlatformAdapter
    private void onGenreClicked(GenresResult genre) {
        if (selectedGenres.contains(genre)) {
            selectedGenres.remove(genre);
        } else {
            selectedGenres.add(genre);
        }
    }
    private void onPlatformClicked(PlatformResult platform) {
        if (selectedPlatforms.contains(platform)) {
            selectedPlatforms.remove(platform);
        } else {
            selectedPlatforms.add(platform);
        }
    }

    // Get the selected Id for genres and platforms
    private String getGenresId(){
        StringBuilder genresString = new StringBuilder();
        for (GenresResult item : selectedGenres){
            genresString.append(item.getId()).append(", ");
        }
        if (genresString.length() > 0) {
            genresString.setLength(genresString.length() - 2);
        }
        return genresString.toString();
    }

    private String getPlatformsId(){
        StringBuilder platformsString = new StringBuilder();
        for (PlatformResult item : selectedPlatforms){
            platformsString.append(item.getId()).append(", ");
        }
        if (platformsString.length() > 0) {
            platformsString.setLength(platformsString.length() - 2);
        }
        return platformsString.toString();
    }

    // Clear all selected items
    private void clearSelectedFilters() {
        if (genersAdapter != null) {
            selectedGenres.clear();

            ArrayList<GenresResult> genres = genersAdapter.getPost();
            if (genres != null) {
                for (GenresResult genre : genres) {
                    genre.setClicked(false);
                }
            }
        }

        if (platformAdapter != null) {
            selectedPlatforms.clear();
            ArrayList<PlatformResult> platforms = platformAdapter.getPost();
            if (platforms != null) {
                for (PlatformResult platform : platforms) {
                    platform.setClicked(false);
                }
            }
        }
    }

}


