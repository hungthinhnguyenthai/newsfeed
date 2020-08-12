package com.thinhnguyen.app.newsfeed.listener;

import android.content.Context;

import com.thinhnguyen.app.newsfeed.model.Content;

/**
 * @author thinh.nguyen
 */
public interface HolderActionListener {

    void onAction(Context parent, Content selectedContent);
}
