package com.dorontayar_nirtzameret.mygameslist.model.detailModel;


import com.dorontayar_nirtzameret.mygameslist.model.commonGameModel.*;

import java.util.List;

public class InfoGame {
    private int achievements_count;
    private int added;
    private AddedByStatus added_by_status;
    private int additions_count;
    private List<Object> alternative_names;
    private String background_image;
    private String background_image_additional;
    private Clip clip;
    private int creators_count;
    private String description;
    private String description_raw;
    private List<Developer> developers;
    private String dominant_color;
    private EsrbRating esrb_rating;
    private int game_series_count;
    private List<Genre> genres;
    private int id;
    private int metacritic;
    private List<MetacriticPlatform> metacritic_platforms;
    private String metacritic_url;
    private int movies_count;
    private String name;
    private String name_original;
    private int parent_achievements_count;
    private List<ParentPlatform> parent_platforms;
    private int parents_count;
    private List<PlatformX> platforms;
    private int playtime;
    private List<Publisher> publishers;
    private double rating;
    private int rating_top;
    private List<Rating> ratings;
    private int ratings_count;
    private Reactions reactions;
    private int reddit_count;
    private String reddit_description;
    private String reddit_logo;
    private String reddit_name;
    private String reddit_url;
    private String released;
    private int reviews_count;
    private int reviews_text_count;
    private String saturated_color;
    private int screenshots_count;
    private String slug;
    private List<Store> stores;
    private int suggestions_count;
    private List<Tag> tags;
    private boolean tba;
    private int twitch_count;
    private String updated;
    private Object user_game;
    private String website;
    private int youtube_count;

    public InfoGame(int achievements_count, int added, AddedByStatus added_by_status, int additions_count,
                    List<Object> alternative_names, String background_image, String background_image_additional,
                    Clip clip, int creators_count, String description, String description_raw, List<Developer> developers,
                    String dominant_color, EsrbRating esrb_rating, int game_series_count, List<Genre> genres, int id,
                    int metacritic, List<MetacriticPlatform> metacritic_platforms, String metacritic_url, int movies_count,
                    String name, String name_original, int parent_achievements_count, List<ParentPlatform> parent_platforms,
                    int parents_count, List<PlatformX> platforms, int playtime, List<Publisher> publishers, double rating,
                    int rating_top, List<Rating> ratings, int ratings_count, Reactions reactions, int reddit_count,
                    String reddit_description, String reddit_logo, String reddit_name, String reddit_url, String released,
                    int reviews_count, int reviews_text_count, String saturated_color, int screenshots_count, String slug,
                    List<Store> stores, int suggestions_count, List<Tag> tags, boolean tba, int twitch_count, String updated,
                    Object user_game, String website, int youtube_count) {
        this.achievements_count = achievements_count;
        this.added = added;
        this.added_by_status = added_by_status;
        this.additions_count = additions_count;
        this.alternative_names = alternative_names;
        this.background_image = background_image;
        this.background_image_additional = background_image_additional;
        this.clip = clip;
        this.creators_count = creators_count;
        this.description = description;
        this.description_raw = description_raw;
        this.developers = developers;
        this.dominant_color = dominant_color;
        this.esrb_rating = esrb_rating;
        this.game_series_count = game_series_count;
        this.genres = genres;
        this.id = id;
        this.metacritic = metacritic;
        this.metacritic_platforms = metacritic_platforms;
        this.metacritic_url = metacritic_url;
        this.movies_count = movies_count;
        this.name = name;
        this.name_original = name_original;
        this.parent_achievements_count = parent_achievements_count;
        this.parent_platforms = parent_platforms;
        this.parents_count = parents_count;
        this.platforms = platforms;
        this.playtime = playtime;
        this.publishers = publishers;
        this.rating = rating;
        this.rating_top = rating_top;
        this.ratings = ratings;
        this.ratings_count = ratings_count;
        this.reactions = reactions;
        this.reddit_count = reddit_count;
        this.reddit_description = reddit_description;
        this.reddit_logo = reddit_logo;
        this.reddit_name = reddit_name;
        this.reddit_url = reddit_url;
        this.released = released;
        this.reviews_count = reviews_count;
        this.reviews_text_count = reviews_text_count;
        this.saturated_color = saturated_color;
        this.screenshots_count = screenshots_count;
        this.slug = slug;
        this.stores = stores;
        this.suggestions_count = suggestions_count;
        this.tags = tags;
        this.tba = tba;
        this.twitch_count = twitch_count;
        this.updated = updated;
        this.user_game = user_game;
        this.website = website;
        this.youtube_count = youtube_count;
    }

    public int getAchievements_count() {
        return achievements_count;
    }

    public void setAchievements_count(int achievements_count) {
        this.achievements_count = achievements_count;
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

    public int getAdditions_count() {
        return additions_count;
    }

    public void setAdditions_count(int additions_count) {
        this.additions_count = additions_count;
    }

    public List<Object> getAlternative_names() {
        return alternative_names;
    }

    public void setAlternative_names(List<Object> alternative_names) {
        this.alternative_names = alternative_names;
    }

    public String getBackground_image() {
        return background_image;
    }

    public void setBackground_image(String background_image) {
        this.background_image = background_image;
    }

    public String getBackground_image_additional() {
        return background_image_additional;
    }

    public void setBackground_image_additional(String background_image_additional) {
        this.background_image_additional = background_image_additional;
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public int getCreators_count() {
        return creators_count;
    }

    public void setCreators_count(int creators_count) {
        this.creators_count = creators_count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription_raw() {
        return description_raw;
    }

    public void setDescription_raw(String description_raw) {
        this.description_raw = description_raw;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    public String getDominant_color() {
        return dominant_color;
    }

    public void setDominant_color(String dominant_color) {
        this.dominant_color = dominant_color;
    }

    public EsrbRating getEsrb_rating() {
        return esrb_rating;
    }

    public void setEsrb_rating(EsrbRating esrb_rating) {
        this.esrb_rating = esrb_rating;
    }

    public int getGame_series_count() {
        return game_series_count;
    }

    public void setGame_series_count(int game_series_count) {
        this.game_series_count = game_series_count;
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

    public List<MetacriticPlatform> getMetacritic_platforms() {
        return metacritic_platforms;
    }

    public void setMetacritic_platforms(List<MetacriticPlatform> metacritic_platforms) {
        this.metacritic_platforms = metacritic_platforms;
    }

    public String getMetacritic_url() {
        return metacritic_url;
    }

    public void setMetacritic_url(String metacritic_url) {
        this.metacritic_url = metacritic_url;
    }

    public int getMovies_count() {
        return movies_count;
    }

    public void setMovies_count(int movies_count) {
        this.movies_count = movies_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_original() {
        return name_original;
    }

    public void setName_original(String name_original) {
        this.name_original = name_original;
    }

    public int getParent_achievements_count() {
        return parent_achievements_count;
    }

    public void setParent_achievements_count(int parent_achievements_count) {
        this.parent_achievements_count = parent_achievements_count;
    }

    public List<ParentPlatform> getParent_platforms() {
        return parent_platforms;
    }

    public void setParent_platforms(List<ParentPlatform> parent_platforms) {
        this.parent_platforms = parent_platforms;
    }

    public int getParents_count() {
        return parents_count;
    }

    public void setParents_count(int parents_count) {
        this.parents_count = parents_count;
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

    public List<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
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

    public Reactions getReactions() {
        return reactions;
    }

    public void setReactions(Reactions reactions) {
        this.reactions = reactions;
    }

    public int getReddit_count() {
        return reddit_count;
    }

    public void setReddit_count(int reddit_count) {
        this.reddit_count = reddit_count;
    }

    public String getReddit_description() {
        return reddit_description;
    }

    public void setReddit_description(String reddit_description) {
        this.reddit_description = reddit_description;
    }

    public String getReddit_logo() {
        return reddit_logo;
    }

    public void setReddit_logo(String reddit_logo) {
        this.reddit_logo = reddit_logo;
    }

    public String getReddit_name() {
        return reddit_name;
    }

    public void setReddit_name(String reddit_name) {
        this.reddit_name = reddit_name;
    }

    public String getReddit_url() {
        return reddit_url;
    }

    public void setReddit_url(String reddit_url) {
        this.reddit_url = reddit_url;
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

    public int getScreenshots_count() {
        return screenshots_count;
    }

    public void setScreenshots_count(int screenshots_count) {
        this.screenshots_count = screenshots_count;
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

    public int getTwitch_count() {
        return twitch_count;
    }

    public void setTwitch_count(int twitch_count) {
        this.twitch_count = twitch_count;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Object getUser_game() {
        return user_game;
    }

    public void setUser_game(Object user_game) {
        this.user_game = user_game;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getYoutube_count() {
        return youtube_count;
    }

    public void setYoutube_count(int youtube_count) {
        this.youtube_count = youtube_count;
    }
}
