package com.dorontayar_nirtzameret.mygameslist.model.commonGameModel;

public class ParentPlatform {
    private PlatformX platform;

    public ParentPlatform(PlatformX platform) {
        this.platform = platform;
    }

    public PlatformX getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformX platform) {
        this.platform = platform;
    }
}

