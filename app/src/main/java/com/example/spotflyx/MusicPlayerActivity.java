package com.example.spotflyx;

import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.android.exoplayer2.util.Util;

public abstract class MusicPlayerActivity extends AppCompatActivity{}/*  implements ExoPlayer.EventListener {
    private SimpleExoPlayer mSimpleExoPlayer;

    private SimpleExoPlayerView mSimpleExoPlayerView;

    private Handler mMainHandler;
    private AdaptiveTrackSelection.Factory mAdaptiveTrackSelectionFactory;
    private TrackSelector mTrackSelector;
    private LoadControl mLoadControl;
    private DefaultBandwidthMeter mBandwidthMeter;
    private DataSource.Factory mDataSourceFactory;
    private SimpleCache mSimpleCache;
    private DataSource.Factory mFactory;
    private MediaSource mVideoSource;
    private LoopingMediaSource mLoopingMediaSource;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        mSimpleExoPlayerView = (SimpleExoPlayerView) findViewById(R.id.videoPlayer);
        mProgressBar = (ProgressBar) findViewById(R.id.amPrgbrLoading);
    }
    private void playMedia() {
        mBandwidthMeter = new DefaultBandwidthMeter();
        mAdaptiveTrackSelectionFactory = new AdaptiveTrackSelection.Factory(mBandwidthMeter);
        mTrackSelector = new DefaultTrackSelector(mAdaptiveTrackSelectionFactory);

        mLoadControl = new DefaultLoadControl();

        mSimpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, mTrackSelector, mLoadControl);

        mSimpleExoPlayerView.setPlayer(mSimpleExoPlayer);

        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "com.exoplayerdemo"), bandwidthMeter);

        // Produces Extractor instances for parsing the media data.
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        // This is the MediaSource representing the media to be played.
        MediaSource videoSource = new ExtractorMediaSource(Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"),
                dataSourceFactory, extractorsFactory, null, null);

        // Prepare the player with the source.
        mLoopingMediaSource = new LoopingMediaSource(videoSource);

        mSimpleExoPlayer.prepare(videoSource);

        mSimpleExoPlayer.setPlayWhenReady(true);

      /*  mSimpleExoPlayer.addListener(new ExoPlayer.EventListener() {
               @Override
                 public void onTimelineChanged(Timeline timeline, Object manifest) {

                 }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

                if (playbackState == ExoPlayer.STATE_BUFFERING) {
                    mProgressBar.setVisibility(View.VISIBLE);
                } else {
                    mProgressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
           /* @Override
            public void onPositionDiscontinuity() {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        playMedia();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopMedia();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopMedia();
    }

    private void stopMedia() {
        mSimpleExoPlayer.stop();
        mSimpleExoPlayer.release();
    }
}
*/