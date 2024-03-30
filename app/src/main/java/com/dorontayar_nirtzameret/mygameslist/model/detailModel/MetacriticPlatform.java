package com.dorontayar_nirtzameret.mygameslist.model.detailModel;

import com.dorontayar_nirtzameret.mygameslist.model.commonGameModel.*;

public class MetacriticPlatform {
    private int metascore;
    private Platform platform;
    private String url;

    public MetacriticPlatform(int metascore, Platform platform, String url) {
        this.metascore = metascore;
        this.platform = platform;
        this.url = url;
    }

    public int getMetascore() {
        return metascore;
    }

    public void setMetascore(int metascore) {
        this.metascore = metascore;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
