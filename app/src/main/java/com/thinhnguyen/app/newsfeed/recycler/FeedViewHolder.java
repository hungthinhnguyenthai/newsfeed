package com.thinhnguyen.app.newsfeed.recycler;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thinhnguyen.app.newsfeed.R;
import com.thinhnguyen.app.newsfeed.listener.ItemClickListener;

/**
 * @author thinh.nguyen
 */
public class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    public TextView txtTitle;
    private ItemClickListener listener;

    public FeedViewHolder(@NonNull View itemView) {
        super(itemView);
        txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view, getAdapterPosition(), false);
    }

    @Override
    public boolean onLongClick(View view) {
        listener.onClick(view, getAdapterPosition(), true);
        return true;
    }

    public void setListener(ItemClickListener listener) {
        this.listener = listener;
    }
}
