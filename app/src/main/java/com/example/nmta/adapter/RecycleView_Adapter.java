package com.example.nmta.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nmta.R;
import com.example.nmta.data.VideoItem.VideoItem;

import java.util.ArrayList;
import java.util.List;

public class RecycleView_Adapter extends RecyclerView.Adapter<RecycleView_Adapter.ReycleView_Holder>{
    List<VideoItem> videoItems;
    Context context;

    public RecycleView_Adapter(List<VideoItem> videoItems, Context context) {
        this.videoItems = videoItems;
        this.context = context;
    }

    public RecycleView_Adapter() {
    }

    @NonNull
    @Override
    public ReycleView_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview, parent, false);
        return new ReycleView_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReycleView_Holder holder, int position) {
        ExoPlayer exoPlayer = new ExoPlayer.Builder(context).build();
        Uri uri = Uri.parse(videoItems.get(position).getUri());
        if(uri != null){
            MediaItem mediaItem = MediaItem.fromUri(uri);
            exoPlayer.setMediaItem(mediaItem);
            exoPlayer.prepare();
            exoPlayer.play();
            holder.playerView.setPlayer(exoPlayer);
        }
        holder.textView.setText(videoItems.get(position).getListUser());
    }

    @Override
    public int getItemCount() {
        return videoItems.size();
    }

    public static class ReycleView_Holder extends RecyclerView.ViewHolder{
        private PlayerView playerView;
        private TextView textView;

        public ReycleView_Holder(@NonNull View itemView) {
            super(itemView);
            playerView = itemView.findViewById(R.id.item_view);
            textView = itemView.findViewById(R.id.item_text);
        }
    }
}

