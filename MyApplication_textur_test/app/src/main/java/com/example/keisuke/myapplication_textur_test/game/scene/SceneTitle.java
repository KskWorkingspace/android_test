package com.example.keisuke.myapplication_textur_test.game.scene;

import android.util.Log;

import com.example.keisuke.myapplication_textur_test.R;
import com.example.keisuke.myapplication_textur_test.entity.texture.TextureEntity;

/**
 * Created by keisuke on 2016/09/25.
 */
public class SceneTitle extends SceneBase {

    TextureEntity mTexture;

    public SceneTitle() {
        mTexture = null;
    }

    @Override
    public void Init() {
        mTexture = new TextureEntity(R.drawable.test);
        mTexture.SetPos(1.0f, 1.0f);
        mTexture.SetScale(1.0f, 1.0f);
        mTexture.SetAngle(0.0f);
    }

    @Override
    public void Update() {
        mTexture.AddAngle(5.0f);
    }

    @Override
    public void Draw() {
        mTexture.Draw();
    }

    @Override
    public void Terminate() {

        mTexture.Terminate();
    }
}
