package com.thinhnguyen.app.newsfeed.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author thinh.nguyen
 */
public class DataStore implements Serializable {

    private List<News> newsList = Arrays.asList();

    public DataStore(){
        newsList.add(
                new News("Thanh Niên",
                        new Channel("Thời sự","https://thanhnien.vn/rss/thoi-su.rss"),
                        new Channel("Thế giới","https://thanhnien.vn/rss/the-gioi.rss"),
                        new Channel("Văn hóa","https://thanhnien.vn/rss/van-hoa.rss"),
                        new Channel("Giải trí","https://thanhnien.vn/rss/giai-tri.rss"),
                        new Channel("Thể thao","https://thethao.thanhnien.vn/rss/home.rss"),
                        new Channel("Đời sống","https://thanhnien.vn/rss/doi-song.rss"),
                        new Channel("Kinh doanh","https://thanhnien.vn/rss/tai-chinh-kinh-doanh.rss"))
        );

    }

}
