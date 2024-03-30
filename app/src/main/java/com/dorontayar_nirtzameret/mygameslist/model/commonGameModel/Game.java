package com.dorontayar_nirtzameret.mygameslist.model.commonGameModel;

public class Game {
    private int added;
    private int id;
    private String name;
    private String slug;

    public Game(int added, int id, String name, String slug) {
        this.added = added;
        this.id = id;
        this.name = name;
        this.slug = slug;
    }

    public int getAdded() {
        return added;
    }

    public void setAdded(int added) {
        this.added = added;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
