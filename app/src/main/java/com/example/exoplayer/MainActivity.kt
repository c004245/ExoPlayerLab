package com.example.exoplayer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var playWhenReady: Boolean = true
    private var currentWindow: Int = 0
    private var playbackPosition: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun initializePlayer() {
        val player = ExoPlayerFactory.newSimpleInstance(
            DefaultRenderersFactory(this),
            DefaultTrackSelector(), DefaultLoadControl()) as SimpleExoPlayer

        video_view.player = player

        player.playWhenReady = playWhenReady
        player.seekTo(currentWindow, playbackPosition)

        val uri = Uri.parse("http://storage.googleapis.com/exoplayer-test-media-0/play.mp3")
        val mediaSource = buildMediaSource(uri)
        player.prepare(mediaSource, true, false)
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        return ExtractorMediaSource.Factory(
            DefaultHttpDataSourceFactory("exoplayer"))
            .createMediaSource(uri)
    }



}
