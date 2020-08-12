package com.thinhnguyen.app.newsfeed.model;

/**
 * @author thinh.nguyen
 */
public class Item implements Content {

    private String title;
    private String description;
    private String link;

    public Item(String title, String description, String link) {
        this.title = title;
        this.description = description;
        this.link = link;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
