package com.dorontayar_nirtzameret.mygameslist.model.genresModel;

import java.util.List;

public class GenresModel {
    private int count;
    private String description;
    private Object next;
    private Object previous;
    private List<Result> results;
    private String seo_description;
    private String seo_h1;
    private String seo_title;

    public GenresModel(int count, String description, Object next, Object previous, List<Result> results, String seo_description, String seo_h1, String seo_title) {
        this.count = count;
        this.description = description;
        this.next = next;
        this.previous = previous;
        this.results = results;
        this.seo_description = seo_description;
        this.seo_h1 = seo_h1;
        this.seo_title = seo_title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getNext() {
        return next;
    }

    public void setNext(Object next) {
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

    public String getSeo_description() {
        return seo_description;
    }

    public void setSeo_description(String seo_description) {
        this.seo_description = seo_description;
    }

    public String getSeo_h1() {
        return seo_h1;
    }

    public void setSeo_h1(String seo_h1) {
        this.seo_h1 = seo_h1;
    }

    public String getSeo_title() {
        return seo_title;
    }

    public void setSeo_title(String seo_title) {
        this.seo_title = seo_title;
    }
}
