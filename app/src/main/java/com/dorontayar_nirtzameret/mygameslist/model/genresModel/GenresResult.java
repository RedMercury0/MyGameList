package com.dorontayar_nirtzameret.mygameslist.model.genresModel;

import com.dorontayar_nirtzameret.mygameslist.model.commonGameModel.Game;

import java.util.List;

public class GenresResult {
    private boolean following;
    private List<Game> games;
    private int games_count;
    private int id;
    private String image_background;
    private String name;
    private String slug;
    private boolean clicked = false;

    public GenresResult(boolean following, List<Game> games, int games_count, int id,
                        String image_background, String name, String slug) {
        this.following = following;
        this.games = games;
        this.games_count = games_count;
        this.id = id;
        this.image_background = image_background;
        this.name = name;
        this.slug = slug;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
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

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}
