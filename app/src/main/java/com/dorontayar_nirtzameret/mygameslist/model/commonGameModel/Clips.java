package com.dorontayar_nirtzameret.mygameslist.model.commonGameModel;

public class Clips {
    private String _320;
    private String _640;
    private String full;

    public Clips(String _320, String _640, String full) {
        this._320 = _320;
        this._640 = _640;
        this.full = full;
    }

    public String get_320() {
        return _320;
    }

    public void set_320(String _320) {
        this._320 = _320;
    }

    public String get_640() {
        return _640;
    }

    public void set_640(String _640) {
        this._640 = _640;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }
}
