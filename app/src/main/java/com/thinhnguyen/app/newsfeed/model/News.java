package com.thinhnguyen.app.newsfeed.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class News implements WrappableContent<Channel>, Serializable {
    private String name;
    private List<Channel> channels;

    public News(){

    }


    public News(String name, Channel ... channels) {
        this.name = name;
        this.channels = Arrays.asList(channels);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    @Override
    public Channel get(int pos) {
        return channels.get(pos);
    }

    @Override
    public int size() {
        return channels.size();
    }
}
