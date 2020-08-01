package com.sanmidev.mybakingapp.feature.recipeStep

import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.sanmidev.mybakingapp.R

internal class ExoComponent(
    val context: Context,
    val lifecycle: Lifecycle,
    val playerView: PlayerView,
    val onError: (ExoPlaybackException) -> Unit
) : LifecycleObserver {

    private var player: SimpleExoPlayer? = null

    init {
        lifecycle.addObserver(this)
    }

    fun initPlayer(url: String) {
        if (player == null) {
            player = SimpleExoPlayer.Builder(context).build()
        }
        val mediaSource = buildMediaSource(url)

        player?.prepare(mediaSource, true, true)
        player?.playWhenReady = true
        player?.addListener(playerListener)
        playerView.player = player
        playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
        player?.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
    }

    private fun buildMediaSource(url: String): MediaSource {

        val uri = Uri.parse(url)
        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
            context, Util.getUserAgent(context, context.getString(R.string.app_name))
        )
        return ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            playerView.onResume()
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            playerView.onPause()
        }
        player?.release()
        player = null
    }

    private val playerListener = object : Player.EventListener {
        override fun onPlayerError(error: ExoPlaybackException) {
            super.onPlayerError(error)
            onError(error)
        }

    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pause() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            playerView.onPause()
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resume() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            playerView.onResume()

        }

    }
}