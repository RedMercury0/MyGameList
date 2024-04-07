package com.dorontayar_nirtzameret.mygameslist.model.bookmarksModel;

public class BookmarkModel {

    private String description;
    private String background_image;
    private String genres;
    private String platforms;
    private String publisher;
    private String game_name;
    private String game_id;
    private String slug;

    public BookmarkModel() {
    }
    public BookmarkModel(String description, String background_image, String genres, String platforms, String publisher, String game_name,String game_id,String slug) {
        this.description = description;
        this.background_image = background_image;
        this.genres = genres;
        this.platforms = platforms;
        this.publisher = publisher;
        this.game_name = game_name;
        this.game_id = game_id;
        this.slug = slug;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackground_image() {
        return background_image;
    }

    public void setBackground_image(String background_image) {
        this.background_image = background_image;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String platforms) {
        this.platforms = platforms;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }
}
