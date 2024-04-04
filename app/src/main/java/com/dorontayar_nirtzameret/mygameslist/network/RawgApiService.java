package com.dorontayar_nirtzameret.mygameslist.network;

import com.dorontayar_nirtzameret.mygameslist.model.detailModel.InfoGame;
import com.dorontayar_nirtzameret.mygameslist.model.genresModel.GenresModel;
import com.dorontayar_nirtzameret.mygameslist.model.platformModel.PlatformModel;
import com.dorontayar_nirtzameret.mygameslist.model.popularModel.TopGames;
import com.dorontayar_nirtzameret.mygameslist.model.searchModel.SearchModel;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RawgApiService {

    // Endpoints for retrieving popular games
    @GET("/api/games")
    Single<TopGames> getGames(@Query("dates") String dates,
                              @Query("ordering") String ordering,
                              @Query("key") String apiKey,
                              @Query("page") int page);


    // Endpoint for retrieving game information by name
    @GET("/api/games/{slug}")
    Single<InfoGame> getGameInfo(@Path("slug") String name, @Query("key") String apiKey);

    // Endpoints for searching games
    @GET("/api/games")
    Single<SearchModel> searchGames(@Query("search_precise") Boolean searchPrecise,@Query("page_size") String pageSize, @Query("search") String query, @Query("page") int page, @Query("key") String apiKey);

    @GET("/api/games")
    Single<SearchModel> searchGamesByGenre(@Query("search_precise") Boolean searchPrecise,@Query("page_size") String pageSize, @Query("search") String query, @Query("genres") String genres, @Query("page") int page, @Query("key") String apiKey);

    @GET("/api/games")
    Single<SearchModel> searchGamesByPlatform(@Query("search_precise") Boolean searchPrecise,@Query("page_size") String pageSize, @Query("search") String query, @Query("platforms") String platforms, @Query("page") int page, @Query("key") String apiKey);

    @GET("/api/games")
    Single<SearchModel> searchGamesByFilters(@Query("search_precise") Boolean searchPrecise,@Query("page_size") String pageSize, @Query("search") String query,@Query("genres") String genres, @Query("platforms") String platforms, @Query("page") int page, @Query("key") String apiKey);

    // Endpoint for retrieving genres
    @GET("/api/genres")
    Single<GenresModel> getGenres(@Query("key") String apiKey);

    // Endpoint for retrieving platforms
    @GET("/api/platforms")
    Single<PlatformModel> getPlatforms(@Query("key") String apiKey);
}
