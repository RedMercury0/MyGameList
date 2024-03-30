package com.dorontayar_nirtzameret.mygameslist.model.detailModel;

public class Store {
    private int id;
    private StoreX store;
    private String url;

    public Store(int id, StoreX store, String url) {
        this.id = id;
        this.store = store;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StoreX getStore() {
        return store;
    }

    public void setStore(StoreX store) {
        this.store = store;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

