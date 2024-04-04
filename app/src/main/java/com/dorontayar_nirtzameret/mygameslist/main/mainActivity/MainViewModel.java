package com.dorontayar_nirtzameret.mygameslist.main.mainActivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dorontayar_nirtzameret.mygameslist.main.ui.home.HomeViewModel;


public class MainViewModel extends AndroidViewModel {

    private HomeViewModel homeViewModel;
    private MutableLiveData<Boolean> dataLoadingComplete = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        homeViewModel = new HomeViewModel(application);
    }

    // Method to fetch data (preloading)
    public void preloadData() {
        homeViewModel.fetchTopGames(1);
        homeViewModel.fetchLatestGames(1);

        // Set the data loading complete flag to true
        dataLoadingComplete.setValue(true);
    }
    public LiveData<Boolean> getDataLoadingComplete() {
        return dataLoadingComplete;
    }
}


