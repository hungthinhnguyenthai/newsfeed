package com.thinhnguyen.app.newsfeed.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author thinh.nguyen
 */
public class ItemWrapper implements WrappableContent<Item> {


    private List<Item> data = new ArrayList<>();

    public void add(Item item) {
        data.add(item);
    }

    @Override
    public Item get(int pos) {
        return data.get(pos);
    }

    @Override
    public int size() {
        return data.size();
    }
}
