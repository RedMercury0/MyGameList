package com.dorontayar_nirtzameret.mygameslist.model.platformModel;


import com.dorontayar_nirtzameret.mygameslist.model.commonGameModel.Game;

import java.util.List;

public class Result {
    private List<Game> games;
    private int games_count;
    private int id;
    private String image;
    private String image_background;
    private String name;
    private String slug;
    private String year_end;
    private String year_start;
    private boolean clicked;

    public Result(List<Game> games, int games_count, int id, String image, String image_background,
                  String name, String slug, String year_end, String year_start, boolean clicked) {
        this.games = games;
        this.games_count = games_count;
        this.id = id;
        this.image = image;
        this.image_background = image_background;
        this.name = name;
        this.slug = slug;
        this.year_end = year_end;
        this.year_start = year_start;
        this.clicked = clicked;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public int getGames_count() {
        return games_count;
    }

    public void setGames_count(int games_count) {
        this.games_count = games_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_background() {
        return image_background;
    }

    public void setImage_background(String image_background) {
        this.image_background = image_background;
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

    public String getYear_end() {
        return year_end;
    }

    public void setYear_end(String year_end) {
        this.year_end = year_end;
    }

    public String getYear_start() {
        return year_start;
    }

    public void setYear_start(String year_start) {
        this.year_start = year_start;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}
