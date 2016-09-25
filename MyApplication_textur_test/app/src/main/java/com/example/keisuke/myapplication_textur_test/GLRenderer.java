package com.example.keisuke.myapplication_textur_test;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.example.keisuke.myapplication_textur_test.utility.GraphicUtility;
import com.example.keisuke.myapplication_textur_test.game.GameMain;

/**
 * Created by keisuke on 2016/09/25.
 */
public class GLRenderer implements GLSurfaceView.Renderer{
    //! blend state.
    public static final int defaultState = 0;
    public static final int addState = 1;
    public static final int multiplyState = 2;
    private int blendState = defaultState;

    //! viewport.
    private int mWidth;
    private int mHeight;

    //! main scene.
    private GameMain gameMain;

    //! constructor.
    public GLRenderer(Context context) {
        if (context == null)
        {
            Log.e("ERR", "context not initialized");
        }
        else
        {
            GraphicUtility.gContext = context;
            gameMain = new GameMain();
        }
    }

    private void renderBegin() {
        //! Setting of the coordinate system.
        GraphicUtility.gGl.glViewport(0, 0, this.mWidth, this.mHeight);
        GraphicUtility.gGl.glMatrixMode(GL10.GL_PROJECTION);

        GraphicUtility.gGl.glLoadIdentity();
        GraphicUtility.gGl.glOrthof(-1.0f, 1.0f, -1.5f, 1.5f, 0.5f, -0.5f);
        GraphicUtility.gGl.glMatrixMode(GL10.GL_MODELVIEW);
        GraphicUtility.gGl.glLoadIdentity();

        //! Clear screen.
        GraphicUtility.gGl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        GraphicUtility.gGl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        //! alpha blend.
		/*
		gl.glEnable(GL10.GL_BLEND);

		switch(this.blendState) {
		case defaultState:
			gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
			break;
		case addState:
			gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE);
			break;
		case multiplyState:
			gl.glBlendFunc(GL10.GL_ZERO, GL10.GL_SRC_COLOR);
			break;
		}
		*/
    }

    private void renderEnd() {
		/*
		gl.glDisable(GL10.GL_BLEND);
		*/
    }

    /*
     * @see android.opengl.GLSurfaceView.Renderer#onDrawFrame(javax.microedition.khronos.opengles.GL10)
     * @brief drawing process main.
     */
    @Override
    public void onDrawFrame(GL10 gl) {
        //! main update.
        gameMain.Update();
        //! Drawing processing main.
        renderBegin();
        gameMain.Draw();
        renderEnd();
    }

    /*
     * @see android.opengl.GLSurfaceView.Renderer#onSurfaceCreated(javax.microedition.khronos.opengles.GL10, javax.microedition.khronos.egl.EGLConfig)
     * @brief call at the time of generating and screen orientation change.
     * @brief initialize process.
     */
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // TODO 自動生成されたメソッド・スタブ
    }

    /*
     * @see android.opengl.GLSurfaceView.Renderer#onSurfaceChanged(javax.microedition.khronos.opengles.GL10, int, int)
     * @brief call at the time of generating and screen orientation change.
     * @brief initialize process.
     */
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // TODO 自動生成されたメソッド・スタブ
        Log.d("DEBUG", "onSurfaceChanged");
        GraphicUtility.gGl = gl;
        this.mWidth = width;
        this.mHeight = height;
    }
}
