package com.dorontayar_nirtzameret.mygameslist.model.detailModel;

public class EsrbRating {
    private int id;
    private String name;
    private String slug;

    public EsrbRating(int id, String name, String slug) {
        this.id = id;
        this.name = name;
        this.slug = slug;
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
