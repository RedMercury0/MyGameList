package com.dorontayar_nirtzameret.mygameslist.model.commonGameModel;

public class Rating {
    private int count;
    private int id;
    private double percent;
    private String title;

    public Rating(int count, int id, double percent, String title) {
        this.count = count;
        this.id = id;
        this.percent = percent;
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

