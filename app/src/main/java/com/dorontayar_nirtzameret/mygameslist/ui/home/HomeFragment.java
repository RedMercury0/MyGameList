package com.dorontayar_nirtzameret.mygameslist.ui.home;

import android.os.Bundle;
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

import com.dorontayar_nirtzameret.mygameslist.adapter.LatestAdapter;
import com.dorontayar_nirtzameret.mygameslist.adapter.TopAdapter;
import com.dorontayar_nirtzameret.mygameslist.databinding.FragmentHomeBinding;
import com.dorontayar_nirtzameret.mygameslist.model.popularModel.Result;
import com.dorontayar_nirtzameret.mygameslist.utilities.NetworkState;

import java.util.List;

public class HomeFragment extends Fragment implements TopAdapter.OnClickAdapterListner, LatestAdapter.OnClickAdapterListner {

    private FragmentHomeBinding binding;
    private TopAdapter topAdapter;
    private LatestAdapter latestAdapter;
    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


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
    }
}
