package com.dorontayar_nirtzameret.mygameslist.network;

import android.content.Context;

import com.dorontayar_nirtzameret.mygameslist.model.detailModel.InfoGame;
import com.dorontayar_nirtzameret.mygameslist.model.genresModel.GenresModel;
import com.dorontayar_nirtzameret.mygameslist.model.platformModel.PlatformModel;
import com.dorontayar_nirtzameret.mygameslist.model.popularModel.TopGames;
import com.dorontayar_nirtzameret.mygameslist.model.searchModel.SearchModel;

import io.reactivex.Single;

public class ApiManager {
    private static RawgApiService apiService;

    // Initialize the API service
    public static void initialize(Context context) {
        apiService = RetrofitClient.getClient(context).create(RawgApiService.class);
    }

    // Method to get popular games
    public static Single<TopGames> getPopularGames(Context context, String dates, String ordering, String apiKey, int page) {
        return apiService.getGames(dates, ordering, apiKey,page);
    }


    // Method to get game info by name
    public static Single<InfoGame> getGameInfo(Context context, String name, String apiKey) {
        return apiService.getGameInfo(name, apiKey);
    }
    // Method to search games
    public static Single<SearchModel> searchGames(Context context, String pageSize, String query, int page, String apiKey) {
        return apiService.searchGames(true,pageSize, query, page, apiKey);
    }

    // Method to search games by genre
    public static Single<SearchModel> searchGamesByGenre(Context context, String pageSize, String query, String genres, int page, String apiKey) {
        return apiService.searchGamesByGenre(true,pageSize, query, genres, page, apiKey);
    }

    // Method to search games by platform
    public static Single<SearchModel> searchGamesByPlatform(Context context, String pageSize, String query, String platforms, int page, String apiKey) {
        return apiService.searchGamesByPlatform(true,pageSize, query, platforms, page, apiKey);
    }
    // Method to search games by platform and genre
    public static Single<SearchModel> searchGamesByFilters(Context context, String pageSize, String query, String platforms, String genres, int page, String apiKey) {
        return apiService.searchGamesByFilters(true,pageSize, query, platforms, genres, page, apiKey);
    }

    // Method to get genres
    public static Single<GenresModel> getGenres(Context context, String apiKey) {
        return apiService.getGenres(apiKey);
    }

    // Method to get platforms
    public static Single<PlatformModel> getPlatforms(Context context, String apiKey) {
        return apiService.getPlatforms(apiKey);
    }
}
