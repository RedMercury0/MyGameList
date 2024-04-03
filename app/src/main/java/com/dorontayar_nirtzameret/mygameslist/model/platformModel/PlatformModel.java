package com.dorontayar_nirtzameret.mygameslist.model.platformModel;


import java.util.List;

public class PlatformModel {
    private int count;
    private String next;
    private Object previous;
    private List<PlatformResult> results;

    public PlatformModel(int count, String next, Object previous, List<PlatformResult> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public List<PlatformResult> getResults() {
        return results;
    }

    public void setResults(List<PlatformResult> results) {
        this.results = results;
    }
}
