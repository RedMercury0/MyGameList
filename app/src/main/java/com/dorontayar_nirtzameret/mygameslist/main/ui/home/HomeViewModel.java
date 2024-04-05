package com.dorontayar_nirtzameret.mygameslist.main.ui.home;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.dorontayar_nirtzameret.mygameslist.R;
import com.dorontayar_nirtzameret.mygameslist.main.previewActivity.PreviewGameActivity;
import com.dorontayar_nirtzameret.mygameslist.model.detailModel.InfoGame;
import com.dorontayar_nirtzameret.mygameslist.model.popularModel.Result;
import com.dorontayar_nirtzameret.mygameslist.model.popularModel.TopGames;
import com.dorontayar_nirtzameret.mygameslist.network.ApiManager;
import com.dorontayar_nirtzameret.mygameslist.utilities.NetworkState;
import com.google.gson.Gson;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<List<Result>> topGames = new MutableLiveData<>();
    private MutableLiveData<List<Result>> latestGames = new MutableLiveData<>();
    private String dates;
    private String apiKey;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        Context context = application.getApplicationContext();
        dates = getDates();
        apiKey = context.getString(R.string.RAWG_API_KEY);
    }

    public MutableLiveData<List<Result>> getTopGames() {
        return topGames;
    }

    public MutableLiveData<List<Result>> getLatestGames() {
        return latestGames;
    }

    public void fetchTopGames(int page) {
        Log.d("HomeViewModel", "Fetching top games from API");
        if (NetworkState.isNetworkAvailable(getApplication().getApplicationContext())) {
            ApiManager.getPopularGames(getApplication().getApplicationContext(), dates, "-rating", apiKey, page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<TopGames>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onSuccess(TopGames topGamesResponse) {
                            topGames.setValue(topGamesResponse.getResults());
                        }

                        @Override
                        public void onError(Throwable e) {}
                    });
        }
    }

    public void fetchLatestGames(int page) {
        Log.d("HomeViewModel", "Fetching latest games from API");
        if (NetworkState.isNetworkAvailable(getApplication().getApplicationContext())) {
            ApiManager.getPopularGames(getApplication().getApplicationContext(), dates, "release-date", apiKey, page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<TopGames>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onSuccess(TopGames latestGamesResponse) {
                            latestGames.setValue(latestGamesResponse.getResults());
                        }

                        @Override
                        public void onError(Throwable e) {}
                    });
        }
    }

    private String getDates() {
        LocalDate currentDate = LocalDate.now();
        LocalDate lastMonthDate = LocalDate.now().minusMonths(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDateStr = currentDate.format(formatter);
        String lastMonthDateStr = lastMonthDate.format(formatter);
        return lastMonthDateStr + "," + currentDateStr;
    }

    private void openPreviewActivity(InfoGame infoGame) {
        // Start the PreviewActivity and pass necessary data
        Intent intent = new Intent(getApplication().getApplicationContext(), PreviewGameActivity.class);
        // Serialize InfoGame object into JSON string
        String infoGameJson = new Gson().toJson(infoGame);
        intent.putExtra("infoGame",infoGame.getName());
        intent.putExtra("infoGameJson", infoGameJson);
        getApplication().getApplicationContext().startActivity(intent);
    }
    // Fetch detailed game data from API
    public void fetchDetail(String gameName) {
        // Make API call to get game details
        ApiManager.getGameInfo(getApplication().getApplicationContext(),gameName, apiKey)
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
