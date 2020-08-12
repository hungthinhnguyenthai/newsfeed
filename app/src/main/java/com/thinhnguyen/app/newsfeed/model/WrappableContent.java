package com.thinhnguyen.app.newsfeed.model;


/**
 * @author thinh.nguyen
 */
public interface WrappableContent<T extends Content> {

    T get(int pos);

    int size();

}
