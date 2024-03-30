package com.dorontayar_nirtzameret.mygameslist.model.searchModel;


import java.util.List;

public class SearchModel {
    private int count;
    private String next;
    private Object previous;
    private List<Result> results;
    private boolean user_platforms;

    public SearchModel(int count, String next, Object previous, List<Result> results, boolean user_platforms) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
        this.user_platforms = user_platforms;
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

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public boolean isUser_platforms() {
        return user_platforms;
    }

    public void setUser_platforms(boolean user_platforms) {
        this.user_platforms = user_platforms;
    }
}
