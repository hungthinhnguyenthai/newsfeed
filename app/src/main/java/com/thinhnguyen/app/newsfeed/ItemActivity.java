package com.thinhnguyen.app.newsfeed;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thinhnguyen.app.newsfeed.adapter.FeedAdapter;
import com.thinhnguyen.app.newsfeed.concurrency.FetchFeedTask;
import com.thinhnguyen.app.newsfeed.listener.HolderActionListener;
import com.thinhnguyen.app.newsfeed.model.Channel;
import com.thinhnguyen.app.newsfeed.model.Content;
import com.thinhnguyen.app.newsfeed.model.Item;


public class ItemActivity extends AppCompatActivity {

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        TextView txtInfo = (TextView)findViewById(R.id.txtTitle);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ProgressBar progressBar = new ProgressBar(this);
        progressBar.setIndeterminate(true);
        if(getIntent() != null) {
            Channel info =(Channel) getIntent().getSerializableExtra("channel");
            txtInfo.setText("Items in " +info.getTitle());
            FetchFeedTask feedTask = new FetchFeedTask(this, getBaseContext(), info);
            feedTask.start();
            try {
                feedTask.join();
                FeedAdapter adapter = new FeedAdapter(feedTask.getItems(), getBaseContext(), this, new HolderActionListener() {
                    @Override
                    public void onAction(final Context parent, Content selectedContent) {
                        if(selectedContent instanceof Item) {
                            final Item item = (Item) selectedContent;
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(parent);
                            alertDialogBuilder.setMessage(Html.fromHtml(item.getDescription()));
                            alertDialogBuilder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            alertDialogBuilder.setNegativeButton("More", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink()));
                                    parent.startActivity(browser);
                                }
                            });
                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                        }
                    }
                });
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}