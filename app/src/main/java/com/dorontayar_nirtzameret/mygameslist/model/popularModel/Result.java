package com.dorontayar_nirtzameret.mygameslist.model.popularModel;


import com.dorontayar_nirtzameret.mygameslist.model.commonGameModel.*;

import java.io.Serializable;
import java.util.List;

public class Result implements Serializable {
    private int added;
    private AddedByStatus added_by_status;
    private String background_image;
    private Clip clip;
    private String dominant_color;
    private List<Genre> genres;
    private int id;
    private int metacritic;
    private String name;
    private List<ParentPlatform> parent_platforms;
    private List<Platform> platforms;
    private Integer playtime;
    private Double rating;
    private Integer rating_top;
    private List<Rating> ratings;
    private Integer ratings_count;
    private String released;
    private Integer reviews_count;
    private Integer reviews_text_count;
    private String saturated_color;
    private Object score;
    private List<ShortScreenshot> short_screenshots;
    private String slug;
    private List<Store> stores;
    private Integer suggestions_count;
    private List<Tag> tags;
    private Boolean tba;
    private Object user_game;


    public Result() {
    }

    public Result(int added, AddedByStatus added_by_status, String background_image, Clip clip,
                  String dominant_color, List<Genre> genres, int id, int metacritic, String name,
                  List<ParentPlatform> parent_platforms, List<Platform> platforms, Integer playtime,
                  Double rating, Integer rating_top, List<Rating> ratings, Integer ratings_count, String released,
                  Integer reviews_count, Integer reviews_text_count, String saturated_color, Object score,
                  List<ShortScreenshot> short_screenshots, String slug, List<Store> stores, Integer suggestions_count,
                  List<Tag> tags, Boolean tba, Object user_game) {
        this.added = added;
        this.added_by_status = added_by_status;
        this.background_image = background_image;
        this.clip = clip;
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

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    public Integer getPlaytime() {
        return playtime;
    }

    public void setPlaytime(Integer playtime) {
        this.playtime = playtime;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getRating_top() {
        return rating_top;
    }

    public void setRating_top(Integer rating_top) {
        this.rating_top = rating_top;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public Integer getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(Integer ratings_count) {
        this.ratings_count = ratings_count;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public Integer getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(Integer reviews_count) {
        this.reviews_count = reviews_count;
    }

    public Integer getReviews_text_count() {
        return reviews_text_count;
    }

    public void setReviews_text_count(Integer reviews_text_count) {
        this.reviews_text_count = reviews_text_count;
    }

    public String getSaturated_color() {
        return saturated_color;
    }

    public void setSaturated_color(String saturated_color) {
        this.saturated_color = saturated_color;
    }

    public Object getScore() {
        return score;
    }

    public void setScore(Object score) {
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

    public Integer getSuggestions_count() {
        return suggestions_count;
    }

    public void setSuggestions_count(Integer suggestions_count) {
        this.suggestions_count = suggestions_count;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Boolean getTba() {
        return tba;
    }

    public void setTba(Boolean tba) {
        this.tba = tba;
    }

    public Object getUser_game() {
        return user_game;
    }

    public void setUser_game(Object user_game) {
        this.user_game = user_game;
    }
}