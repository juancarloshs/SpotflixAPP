package com.example.spotflyx;

import android.content.Intent;
import android.os.Bundle;

import com.brightcove.player.event.EventType;
import com.brightcove.player.model.DeliveryType;
import com.brightcove.player.model.Video;
import com.brightcove.player.view.BrightcoveExoPlayerVideoView;
import com.brightcove.player.view.BrightcovePlayer;

public class MoviePlayer extends BrightcovePlayer {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_player);
        Intent intent = getIntent();
        String link = intent.getExtras().getString("Link");

        brightcoveVideoView = (BrightcoveExoPlayerVideoView) findViewById(R.id.brightcove_video_view);
        brightcoveVideoView.isFullScreen();
        Video video = Video.createVideo(link, DeliveryType.HLS);

        brightcoveVideoView.add(video);
        brightcoveVideoView.start();
    }
}
