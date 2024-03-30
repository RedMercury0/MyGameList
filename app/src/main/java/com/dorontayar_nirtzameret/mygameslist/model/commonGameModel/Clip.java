package com.dorontayar_nirtzameret.mygameslist.model.commonGameModel;

public class Clip {
    private String clip;
    private Clips clips;
    private String preview;
    private String video;

    public Clip(String clip, Clips clips, String preview, String video) {
        this.clip = clip;
        this.clips = clips;
        this.preview = preview;
        this.video = video;
    }

    public String getClip() {
        return clip;
    }

    public void setClip(String clip) {
        this.clip = clip;
    }

    public Clips getClips() {
        return clips;
    }

    public void setClips(Clips clips) {
        this.clips = clips;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}

