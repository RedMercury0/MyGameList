package com.dorontayar_nirtzameret.mygameslist.model.detailModel;

public class Platform_Metacritic {
    private int platform;
    private String name;
    private String slug;

    public Platform_Metacritic(int platform, String name, String slug) {
        this.platform = platform;
        this.name = name;
        this.slug = slug;
    }

    public int getPlatform() {
        return platform;
    }

    public void setId(int id) {
        this.platform = platform;
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
