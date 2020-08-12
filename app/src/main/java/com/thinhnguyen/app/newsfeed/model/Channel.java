package com.thinhnguyen.app.newsfeed.model;


public class Channel implements Content {
    private String name;
    private String url;

    public Channel(String name, String url) {
        this.name = name;
        this.url = url;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getTitle() {
        return name;
    }
}

