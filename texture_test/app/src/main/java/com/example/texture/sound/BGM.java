package com.example.texture.sound;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by keisuke on 2016/10/15.
 */
public class BGM {

    private MediaPlayer mMediaPlayer;

    public BGM() {
        mMediaPlayer = null;
    }

    public void setBGM( Context in_context, int res_ID ) {
        this.mMediaPlayer = MediaPlayer.create( in_context, res_ID );
        this.mMediaPlayer.setLooping(true);          //!< ループ再生、BGMだからとりあえず.
        this.mMediaPlayer.setVolume(1.0f, 1.0f);      //!< 左右のボリュームを最大にする.
    }

    // BGMを再生する
    public void start() {
        if (!mMediaPlayer.isPlaying()) {
            mMediaPlayer.seekTo(0);
            mMediaPlayer.start();
        }
    }

    // BGMを停止する
    public void stop() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
            mMediaPlayer.prepareAsync();
        }
    }
}
