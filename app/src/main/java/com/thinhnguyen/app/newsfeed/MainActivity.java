package com.thinhnguyen.app.newsfeed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.thinhnguyen.app.newsfeed.model.Channel;
import com.thinhnguyen.app.newsfeed.model.News;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridLayout mainGrid;
    private List<News> newsList = new ArrayList<>();

    private void initData() {
        News tnNews = new News("Thanh Niên", new Channel("Thời sự","https://thanhnien.vn/rss/thoi-su.rss"),
                new Channel("Thế giới","https://thanhnien.vn/rss/the-gioi.rss"),
                new Channel("Văn hóa","https://thanhnien.vn/rss/van-hoa.rss"),
                new Channel("Giải trí","https://thanhnien.vn/rss/giai-tri.rss"),
                new Channel("Thể thao","https://thethao.thanhnien.vn/rss/home.rss"),
                new Channel("Đời sống","https://thanhnien.vn/rss/doi-song.rss"),
                new Channel("Kinh doanh","https://thanhnien.vn/rss/tai-chinh-kinh-doanh.rss"));
        newsList.add(tnNews);

        News ttNews = new News("Tuổi Trẻ",
                new Channel("Thời sự","https://tuoitre.vn/rss/thoi-su.rss"),
                new Channel("Thế giới","https://tuoitre.vn/rss/the-gioi.rss"),
                new Channel("Văn hóa","https://tuoitre.vn/rss/van-hoa.rss"),
                new Channel("Giải trí","https://tuoitre.vn/rss/giai-tri.rss"),
                new Channel("Thể thao","https://tuoitre.vn/rss/the-thao.rss"),
                new Channel("Nhịp sống trẻ","https://tuoitre.vn/rss/nhip-song-tre.rss"),
                new Channel("Kinh doanh","https://tuoitre.vn/rss/kinh-doanh.rss"));
        newsList.add(ttNews);

        News vnNews = new News("VnExpress",
                new Channel("Thời sự","https://vnexpress.net/rss/thoi-su.rss"),
                new Channel("Thế giới","https://vnexpress.net/rss/the-gioi.rss"),
                new Channel("Giáo dục","https://vnexpress.net/rss/giao-duc.rss"),
                new Channel("Cười","https://vnexpress.net/rss/cuoi.rss"),
                new Channel("Thể thao","https://vnexpress.net/rss/the-thao.rss"),
                new Channel("Khoa học","https://vnexpress.net/rss/khoa-hoc.rss"),
                new Channel("Kinh doanh","https://vnexpress.net/rss/kinh-doanh.rss"));
        newsList.add(vnNews);

        News ploNews = new News("Báo Pháp Luật",
                new Channel("Thời sự","https://plo.vn/rss/thoi-su-1.rss"),
                new Channel("Quốc tế","https://plo.vn/rss/quoc-te-8.rss"),
                new Channel("Văn hóa","https://plo.vn/rss/van-hoa-16.rss"),
                new Channel("Xe và luật","https://plo.vn/rss/xe-va-luat-37.rss"),
                new Channel("Thể thao","https://plo.vn/rss/the-thao-22.rss"),
                new Channel("Pháp luật","https://plo.vn/rss/phap-luat-114.rss"),
                new Channel("Kinh tế","https://plo.vn/rss/kinh-te-13.rss"));
        newsList.add(ploNews);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        setContentView(R.layout.activity_main);
        mainGrid = (GridLayout) findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);
    }
    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, ChannelActivity.class);
                    News selectedNews = newsList.get(finalI);
                    intent.putExtra("news",selectedNews);
                    startActivity(intent);

                }
            });
        }
    }
}
