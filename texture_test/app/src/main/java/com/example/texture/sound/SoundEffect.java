package com.example.texture.sound;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Created by keisuke on 2016/10/16.
 */
public class SoundEffect {
    private SoundPool mSound;
    private int se;
    public SoundEffect() {
        this.mSound = null;
        this.se = 0;
        CreateSound();
    }

    public SoundEffect( Context in_context, int in_resID ) {
        this.mSound = null;
        this.se = 0;
        CreateSound();
        setmSound( in_context, in_resID );
    }

    private void CreateSound() {
        this.mSound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
    }

    public void setmSound( Context in_context, int in_redID ) {
        this.se = mSound.load(in_context, in_redID, 1);
    }

    public void Play() {
        mSound.play(this.se, 1.0f, 1.0f, 1, 0, 1.0f);
    }
}
