package com.dorontayar_nirtzameret.mygameslist.main.ui.home;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.dorontayar_nirtzameret.mygameslist.R;
import com.dorontayar_nirtzameret.mygameslist.model.popularModel.Result;
import com.dorontayar_nirtzameret.mygameslist.model.popularModel.TopGames;
import com.dorontayar_nirtzameret.mygameslist.network.ApiManager;
import com.dorontayar_nirtzameret.mygameslist.utilities.NetworkState;

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






}
