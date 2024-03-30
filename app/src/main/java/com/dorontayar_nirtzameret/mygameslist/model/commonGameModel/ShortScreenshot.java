package com.dorontayar_nirtzameret.mygameslist.model.commonGameModel;

public class ShortScreenshot {
    private int id;
    private String image;

    public ShortScreenshot(int id, String image) {
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
