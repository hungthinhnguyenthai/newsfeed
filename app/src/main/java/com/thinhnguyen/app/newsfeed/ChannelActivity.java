package com.thinhnguyen.app.newsfeed;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thinhnguyen.app.newsfeed.adapter.FeedAdapter;
import com.thinhnguyen.app.newsfeed.listener.HolderActionListener;
import com.thinhnguyen.app.newsfeed.model.Content;
import com.thinhnguyen.app.newsfeed.model.News;

public class ChannelActivity extends AppCompatActivity {

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_main);

        TextView txtInfo = (TextView)findViewById(R.id.txtTitle);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        if(getIntent() != null) {
            News info =(News) getIntent().getSerializableExtra("news");
            txtInfo.setText("Channels in " +info.getName());
            FeedAdapter adapter = new FeedAdapter(info, getBaseContext(), this, new HolderActionListener() {
                @Override
                public void onAction(Context parent, Content selectedContent) {
                    Intent intent = new Intent(parent, ItemActivity.class);
                    intent.putExtra("channel", selectedContent);
                    parent.startActivity(intent);
                }
            });
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}
