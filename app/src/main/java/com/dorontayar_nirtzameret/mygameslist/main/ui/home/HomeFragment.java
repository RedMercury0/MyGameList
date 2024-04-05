package com.dorontayar_nirtzameret.mygameslist.main.ui.home;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dorontayar_nirtzameret.mygameslist.R;
import com.dorontayar_nirtzameret.mygameslist.adapter.LatestAdapter;
import com.dorontayar_nirtzameret.mygameslist.adapter.TopAdapter;
import com.dorontayar_nirtzameret.mygameslist.databinding.FragmentHomeBinding;
import com.dorontayar_nirtzameret.mygameslist.main.previewActivity.PreviewGameActivity;
import com.dorontayar_nirtzameret.mygameslist.model.detailModel.InfoGame;
import com.dorontayar_nirtzameret.mygameslist.model.popularModel.Result;
import com.dorontayar_nirtzameret.mygameslist.network.ApiManager;
import com.dorontayar_nirtzameret.mygameslist.utilities.NetworkState;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends Fragment implements TopAdapter.OnClickAdapterListner, LatestAdapter.OnClickAdapterListner {

    private FragmentHomeBinding binding;
    private TopAdapter topAdapter;
    private LatestAdapter latestAdapter;
    private HomeViewModel homeViewModel;
    private String apiKey;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Get api key
        apiKey = getContext().getString(R.string.RAWG_API_KEY);

        // Initialize ApiManager
        //ApiManager.initialize(requireContext());

        // Initialize the ViewModel
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        // Observe LiveData for top games
        homeViewModel.getTopGames().observe(getViewLifecycleOwner(), new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                updateTopGames(results);
            }
        });

        // Observe LiveData for latest games
        homeViewModel.getLatestGames().observe(getViewLifecycleOwner(), new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                updateLatestGames(results);
            }
        });

        setupTopRecyclerView();
        setupLatestRecyclerView();

        // Check for network connectivity before making network requests
        if (NetworkState.isNetworkAvailable(requireContext())) {
            homeViewModel.fetchTopGames(1);
            homeViewModel.fetchLatestGames(1);
        } else {
            Toast.makeText(requireContext(), "No internet connection", Toast.LENGTH_SHORT).show();
        }

        return root;
    }

    private void updateTopGames(List<Result> results) {
        topAdapter.setPosts(results);
    }

    private void updateLatestGames(List<Result> results) {
        latestAdapter.setPosts(results);
    }

    private void setupTopRecyclerView() {
        topAdapter = new TopAdapter(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerView.setAdapter(topAdapter);
    }

    private void setupLatestRecyclerView() {
        latestAdapter = new LatestAdapter(this);
        binding.recyclerView1.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        binding.recyclerView1.setAdapter(latestAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(Result game) {
        // Handle item click
        fetchDetail(game.getSlug());
    }
    private void openPreviewActivity(InfoGame infoGame) {
        // Start the PreviewActivity and pass necessary data
        Intent intent = new Intent(getContext(), PreviewGameActivity.class);
        // Serialize InfoGame object into JSON string
        String infoGameJson = new Gson().toJson(infoGame);
        intent.putExtra("infoGameJson", infoGameJson);
        startActivity(intent);
    }
    // Fetch detailed game data from API
    public void fetchDetail(String gameName) {
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

}
