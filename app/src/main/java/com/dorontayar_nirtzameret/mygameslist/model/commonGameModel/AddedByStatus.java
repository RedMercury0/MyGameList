package com.dorontayar_nirtzameret.mygameslist.model.commonGameModel;

public class AddedByStatus {
    private int beaten;
    private int dropped;
    private int owned;
    private int playing;
    private int toplay;
    private int yet;

    public AddedByStatus(int beaten, int dropped, int owned, int playing, int toplay, int yet) {
        this.beaten = beaten;
        this.dropped = dropped;
        this.owned = owned;
        this.playing = playing;
        this.toplay = toplay;
        this.yet = yet;
    }

    public int getBeaten() {
        return beaten;
    }

    public void setBeaten(int beaten) {
        this.beaten = beaten;
    }

    public int getDropped() {
        return dropped;
    }

    public void setDropped(int dropped) {
        this.dropped = dropped;
    }

    public int getOwned() {
        return owned;
    }

    public void setOwned(int owned) {
        this.owned = owned;
    }

    public int getPlaying() {
        return playing;
    }

    public void setPlaying(int playing) {
        this.playing = playing;
    }

    public int getToplay() {
        return toplay;
    }

    public void setToplay(int toplay) {
        this.toplay = toplay;
    }

    public int getYet() {
        return yet;
    }

    public void setYet(int yet) {
        this.yet = yet;
    }
}

