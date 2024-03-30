package com.dorontayar_nirtzameret.mygameslist.model.commonGameModel;

public class Tag {
    private int gamesCount;
    private int id;
    private String imageBackground;
    private String language;
    private String name;
    private String slug;

    public Tag(int gamesCount, int id, String imageBackground, String language, String name, String slug) {
        this.gamesCount = gamesCount;
        this.id = id;
        this.imageBackground = imageBackground;
        this.language = language;
        this.name = name;
        this.slug = slug;
    }

    public int getGamesCount() {
        return gamesCount;
    }

    public void setGamesCount(int gamesCount) {
        this.gamesCount = gamesCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageBackground() {
        return imageBackground;
    }

    public void setImageBackground(String imageBackground) {
        this.imageBackground = imageBackground;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}

