package com.dorontayar_nirtzameret.mygameslist.model.detailModel;

public class MetacriticPlatform {
    private int metascore;
    private Platform_Metacritic platform;
    private String url;

    public MetacriticPlatform(int metascore, Platform_Metacritic platform, String url) {
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

    public Platform_Metacritic getPlatform() {
        return platform;
    }

    public void setPlatform(Platform_Metacritic platform) {
        this.platform = platform;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
