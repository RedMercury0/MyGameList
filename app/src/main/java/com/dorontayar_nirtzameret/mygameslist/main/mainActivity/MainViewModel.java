package com.dorontayar_nirtzameret.mygameslist.main.mainActivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.dorontayar_nirtzameret.mygameslist.ui.home.HomeViewModel;


public class MainViewModel extends AndroidViewModel {

    private HomeViewModel homeViewModel;

    public MainViewModel(@NonNull Application application) {
        super(application);
        homeViewModel = new HomeViewModel(application);
    }

    // Method to fetch data (preloading)
    public void preloadData() {
        homeViewModel.fetchTopGames(1);
        homeViewModel.fetchLatestGames(1);
    }
}


