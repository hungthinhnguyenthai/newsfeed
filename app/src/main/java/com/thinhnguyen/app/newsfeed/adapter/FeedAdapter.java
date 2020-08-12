package com.thinhnguyen.app.newsfeed.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thinhnguyen.app.newsfeed.R;
import com.thinhnguyen.app.newsfeed.listener.HolderActionListener;
import com.thinhnguyen.app.newsfeed.listener.ItemClickListener;
import com.thinhnguyen.app.newsfeed.model.WrappableContent;
import com.thinhnguyen.app.newsfeed.recycler.FeedViewHolder;

/**
 * @author thinh.nguyen
 */
public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {

    private LayoutInflater inflater;
    private Context mContext, parent;
    private WrappableContent data;
    private HolderActionListener actionListener;

    public FeedAdapter(WrappableContent data, Context mContext, Context parent, HolderActionListener listener) {
        this.data = data;
        this.mContext = mContext;
        this.parent = parent;
        inflater = LayoutInflater.from(mContext);
        this.actionListener = listener;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.line, parent, false);
        return new FeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        holder.txtTitle.setText(data.get(position).getTitle());
        holder.setListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                actionListener.onAction(parent, data.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
