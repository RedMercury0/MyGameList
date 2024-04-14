package com.dorontayar_nirtzameret.mygameslist.main.filterFragment;

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

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);

        apiKey = getContext().getString(R.string.RAWG_API_KEY);

        fetchGenres();
        fetchPlatforms();

        recyclerView = view.findViewById(R.id.filter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        initializeAdapters();

        avFromCode = view.findViewById(R.id.av_from_code);
        gameFind = view.findViewById(R.id.gameFind);


        view.findViewById(R.id.filterOpen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBottomSheetDialog();
            }
        });

        return view;
    }

    // Initialize FilterAdapter, GenresAdapter, PlatformAdapter
    private void initializeAdapters() {
        filterAdapter = new FilterAdapter(new FilterAdapter.OnClickAdapterListener() {
            @Override
            public void onClick(Result result) {
                fetchDetail(result.getSlug());
            }
        });

        genersAdapter = new GenersAdapter(new GenersAdapter.OnClickAdapterListener() {
            @Override
            public void onClick(GenresResult item, ArrayList<GenresResult> items) {
                item.setClicked(!item.isClicked());
                onGenreClicked(item);
                genersAdapter.notifyDataSetChanged();
            }
        });

        platformAdapter = new PlatformAdapter(new PlatformAdapter.OnClickAdapterListener() {
            @Override
            public void onClick(PlatformResult item, ArrayList<PlatformResult> items) {
                item.setClicked(!item.isClicked());
                onPlatformClicked(item);
                platformAdapter.notifyDataSetChanged();
            }
        });

        recyclerView.setAdapter(filterAdapter);
        recyclerView.setVisibility(View.VISIBLE);


    }

    // Open the bottom sheet dialog for searching
    private void openBottomSheetDialog() {
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottomsheetdialog, null);
        bottomSheetDialog = new BottomSheetDialog(requireContext());
        bottomSheetDialog.setContentView(bottomSheetView);

        RecyclerView genresRecyclerView = bottomSheetView.findViewById(R.id.genresRec);
        RecyclerView platformsRecyclerView = bottomSheetView.findViewById(R.id.platformRec);

        genresRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        platformsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));

        genresRecyclerView.setAdapter(genersAdapter);
        platformsRecyclerView.setAdapter(platformAdapter);

        bottomSheetView.findViewById(R.id.findButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText searchEditText = bottomSheetView.findViewById(R.id.textInsert);
                String searchText = searchEditText.getText().toString();

                //if (!searchText.isEmpty()) {performSearch(searchText, 1);}
                performSearch(searchText, 1);

                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetDialog.show();
    }

    // Start the PreviewActivity and pass necessary data
    private void openPreviewActivity(InfoGame infoGame) {
        Intent intent = new Intent(getContext(), PreviewGameActivity.class);

        // Serialize InfoGame object into JSON string
        String infoGameJson = new Gson().toJson(infoGame);
        intent.putExtra("infoGame",infoGame.getName());
        intent.putExtra("infoGameJson", infoGameJson);

        startActivity(intent);
    }

    // Fetch genres data from the API
    private void fetchGenres() {
        ApiManager.getGenres(getContext(), apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<GenresModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onSuccess(@NonNull GenresModel genresModel) {
                        genersAdapter.setPosts(genresModel.getResults());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "Failed to fetch genres: " + e.getMessage());
                    }
                });
    }

    // Fetch platforms data from the API
    private void fetchPlatforms() {

        ApiManager.getPlatforms(getContext(), apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<PlatformModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onSuccess(@NonNull PlatformModel platformModel) {
                        platformAdapter.setPosts(platformModel.getResults());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "Failed to fetch platforms: " + e.getMessage());
                    }
                });
    }

    // Fetch detailed game data from the API
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

    // Perform search based on the entered text, selected genres, and selected platforms
    private void performSearch(String searchText, int page) {
        if (selectedGenres.isEmpty() && selectedPlatforms.isEmpty()){
            ApiManager.searchGames(getContext(), "25", searchText,page, apiKey)

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<SearchModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onSuccess(@NonNull SearchModel searchModel) {
                        filterAdapter.setPosts(searchModel.getResults());

                        clearSelectedFilters();

                        hideGameFinder();

                        if (bottomSheetDialog != null && bottomSheetDialog.isShowing()) {
                            bottomSheetDialog.dismiss();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
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
                        public void onSubscribe(@NonNull Disposable d) {}

                        @Override
                        public void onSuccess(@NonNull SearchModel searchModel) {
                            filterAdapter.setPosts(searchModel.getResults());

                            clearSelectedFilters();

                            hideGameFinder();

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
                        public void onSubscribe(@NonNull Disposable d) {}

                        @Override
                        public void onSuccess(@NonNull SearchModel searchModel) {
                            filterAdapter.setPosts(searchModel.getResults());

                            clearSelectedFilters();

                            hideGameFinder();

                            if (bottomSheetDialog != null && bottomSheetDialog.isShowing()) {
                                bottomSheetDialog.dismiss();
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.e(TAG, "Search error: " + e.getMessage());
                        }
                    });
            }else {
            ApiManager.searchGamesByFilters(getContext(), "25", searchText, getPlatformsId(),getGenresId(), page, apiKey)

                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<SearchModel>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {}

                        @Override
                        public void onSuccess(@NonNull SearchModel searchModel) {
                            filterAdapter.setPosts(searchModel.getResults());

                            clearSelectedFilters();

                            hideGameFinder();

                            if (bottomSheetDialog != null && bottomSheetDialog.isShowing()) {
                                bottomSheetDialog.dismiss();
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.e(TAG, "Search error: " + e.getMessage());
                        }
                    });
        }


    }

    // Hide the Game Finder animation
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
        Log.w("FilterFragment","Genres ID selected: "+genresString.toString());
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
        Log.w("FilterFragment","Platforms ID selected: "+platformsString.toString());
        return platformsString.toString();
    }

    // Clear all selected items in the filter
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


