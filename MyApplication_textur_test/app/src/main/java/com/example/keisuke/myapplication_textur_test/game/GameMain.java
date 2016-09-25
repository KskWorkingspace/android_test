package com.example.keisuke.myapplication_textur_test.game;

import com.example.keisuke.myapplication_textur_test.game.scene.SceneBase;

/**
 * Continue to add the scene here.
 */
import com.example.keisuke.myapplication_textur_test.game.scene.SceneTitle;


/**
 * Created by keisuke on 2016/09/25.
 */
public class GameMain {
    //! scene index enumerated.
    public static enum ESceneIndex {
        ESceneIndex_Title,

    }

    //! scene index.
    public static ESceneIndex sceneIdx;

    //! scene base.
    private SceneBase mScene;

    public GameMain() {
        sceneIdx = ESceneIndex.ESceneIndex_Title;
        mScene = null;
    }

    public void Update() {

        if ( mScene == null ) {
            switch(sceneIdx) {
                case ESceneIndex_Title:
                    mScene = new SceneTitle();
                    break;
            }

            //! Init.
            if (mScene != null) {
                mScene.Init();
            }
        }

        ESceneIndex tmp = sceneIdx;

        //! Update.
        if ( mScene != null ) {
            mScene.Update();
        }

        //! change scene.
        if (tmp != sceneIdx) {
            mScene.Terminate();
            mScene = null;
        }
    }

    public void Draw() {
        if ( mScene != null ) {
            mScene.Draw();
        }
    }
}
