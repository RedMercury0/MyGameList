package com.dorontayar_nirtzameret.mygameslist.model.searchModel;

import com.dorontayar_nirtzameret.mygameslist.model.commonGameModel.AddedByStatus;
import com.dorontayar_nirtzameret.mygameslist.model.commonGameModel.Clip;
import com.dorontayar_nirtzameret.mygameslist.model.commonGameModel.Genre;
import com.dorontayar_nirtzameret.mygameslist.model.commonGameModel.ParentPlatform;
import com.dorontayar_nirtzameret.mygameslist.model.commonGameModel.PlatformX;
import com.dorontayar_nirtzameret.mygameslist.model.commonGameModel.Rating;
import com.dorontayar_nirtzameret.mygameslist.model.commonGameModel.ShortScreenshot;
import com.dorontayar_nirtzameret.mygameslist.model.commonGameModel.Store;
import com.dorontayar_nirtzameret.mygameslist.model.commonGameModel.Tag;

import java.util.List;

public class Result {
    private int added;
    private AddedByStatus added_by_status;
    private String background_image;
    private Clip clip;
    private int community_rating;
    private String dominant_color;
    private List<Genre> genres;
    private int id;
    private int metacritic;
    private String name;
    private List<ParentPlatform> parent_platforms;
    private List<PlatformX> platforms;
    private int playtime;
    private double rating;
    private int rating_top;
    private List<Rating> ratings;
    private int ratings_count;
    private String released;
    private int reviews_count;
    private int reviews_text_count;
    private String saturated_color;
    private String score;
    private List<ShortScreenshot> short_screenshots;
    private String slug;
    private List<Store> stores;
    private int suggestions_count;
    private List<Tag> tags;
    private boolean tba;
    private Object user_game;

    public Result(int added, AddedByStatus added_by_status, String background_image, Clip clip, int community_rating,
                  String dominant_color, List<Genre> genres, int id, int metacritic, String name, List<ParentPlatform> parent_platforms,
                  List<PlatformX> platforms, int playtime, double rating, int rating_top, List<Rating> ratings, int ratings_count,
                  String released, int reviews_count, int reviews_text_count, String saturated_color, String score,
                  List<ShortScreenshot> short_screenshots, String slug, List<Store> stores, int suggestions_count, List<Tag> tags,
                  boolean tba, Object user_game) {
        this.added = added;
        this.added_by_status = added_by_status;
        this.background_image = background_image;
        this.clip = clip;
        this.community_rating = community_rating;
        this.dominant_color = dominant_color;
        this.genres = genres;
        this.id = id;
        this.metacritic = metacritic;
        this.name = name;
        this.parent_platforms = parent_platforms;
        this.platforms = platforms;
        this.playtime = playtime;
        this.rating = rating;
        this.rating_top = rating_top;
        this.ratings = ratings;
        this.ratings_count = ratings_count;
        this.released = released;
        this.reviews_count = reviews_count;
        this.reviews_text_count = reviews_text_count;
        this.saturated_color = saturated_color;
        this.score = score;
        this.short_screenshots = short_screenshots;
        this.slug = slug;
        this.stores = stores;
        this.suggestions_count = suggestions_count;
        this.tags = tags;
        this.tba = tba;
        this.user_game = user_game;
    }

    public int getAdded() {
        return added;
    }

    public void setAdded(int added) {
        this.added = added;
    }

    public AddedByStatus getAdded_by_status() {
        return added_by_status;
    }

    public void setAdded_by_status(AddedByStatus added_by_status) {
        this.added_by_status = added_by_status;
    }

    public String getBackground_image() {
        return background_image;
    }

    public void setBackground_image(String background_image) {
        this.background_image = background_image;
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public int getCommunity_rating() {
        return community_rating;
    }

    public void setCommunity_rating(int community_rating) {
        this.community_rating = community_rating;
    }

    public String getDominant_color() {
        return dominant_color;
    }

    public void setDominant_color(String dominant_color) {
        this.dominant_color = dominant_color;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMetacritic() {
        return metacritic;
    }

    public void setMetacritic(int metacritic) {
        this.metacritic = metacritic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ParentPlatform> getParent_platforms() {
        return parent_platforms;
    }

    public void setParent_platforms(List<ParentPlatform> parent_platforms) {
        this.parent_platforms = parent_platforms;
    }

    public List<PlatformX> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<PlatformX> platforms) {
        this.platforms = platforms;
    }

    public int getPlaytime() {
        return playtime;
    }

    public void setPlaytime(int playtime) {
        this.playtime = playtime;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRating_top() {
        return rating_top;
    }

    public void setRating_top(int rating_top) {
        this.rating_top = rating_top;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getReviews_text_count() {
        return reviews_text_count;
    }

    public void setReviews_text_count(int reviews_text_count) {
        this.reviews_text_count = reviews_text_count;
    }

    public String getSaturated_color() {
        return saturated_color;
    }

    public void setSaturated_color(String saturated_color) {
        this.saturated_color = saturated_color;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public List<ShortScreenshot> getShort_screenshots() {
        return short_screenshots;
    }

    public void setShort_screenshots(List<ShortScreenshot> short_screenshots) {
        this.short_screenshots = short_screenshots;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public int getSuggestions_count() {
        return suggestions_count;
    }

    public void setSuggestions_count(int suggestions_count) {
        this.suggestions_count = suggestions_count;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public boolean isTba() {
        return tba;
    }

    public void setTba(boolean tba) {
        this.tba = tba;
    }

    public Object getUser_game() {
        return user_game;
    }

    public void setUser_game(Object user_game) {
        this.user_game = user_game;
    }
}
