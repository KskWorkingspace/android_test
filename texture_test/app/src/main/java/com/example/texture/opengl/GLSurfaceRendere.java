package com.example.texture.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import com.example.texture.R;
import com.example.texture.texture.Draw2DTexture;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLSurfaceRendere implements GLSurfaceView.Renderer {

    private GL10 mGL;
    private Context mContext;
    private ArrayList<Draw2DTexture> tTexture;

    private Draw2DTexture tex;

    private int mWidth, mHeight;

    //! constructor.
    public GLSurfaceRendere(Context context) {
        if (context == null)
        {
            Log.e(getClass().toString(), "context not initialized");
        } else {
            this.mContext = context;
        }
    }

    private void renderBegin() {
        //! Clear screen.

        this.mGL.glOrthof(-1.0f, 1.0f, -1.5f, 1.5f, 0.5f, -0.5f);
        this.mGL.glMatrixMode(GL10.GL_MODELVIEW);
        this.mGL.glLoadIdentity();

        this.mGL.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        this.mGL.glClear(GL10.GL_COLOR_BUFFER_BIT);

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

        tex.AddAngle( 1 );

        if ( this.mGL != null ) {
            renderBegin();
            //! 描画.
            if ( tTexture.size() != 0) {
                for (Draw2DTexture tex : tTexture) {
                    tex.Draw(this.mGL);
                }
            }
            renderEnd();
        }
    }

    /*
     * @see android.opengl.GLSurfaceView.Renderer#onSurfaceCreated(javax.microedition.khronos.opengles.GL10, javax.microedition.khronos.egl.EGLConfig)
     * @brief call at the time of generating and screen orientation change.
     * @brief initialize process.
     */
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

    }

    /*
     * @see android.opengl.GLSurfaceView.Renderer#onSurfaceChanged(javax.microedition.khronos.opengles.GL10, int, int)
     * @brief call at the time of generating and screen orientation change.
     * @brief initialize process.
     */
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        this.mGL = gl;
        this.mWidth = width;
        this.mHeight = height;

        if (this.mGL == null) {
            Log.e(getClass().toString(), "GL Initialize failed\n");
            return;
        }

        //! ビューポート設定.
        this.mGL.glViewport( 0, 0, this.mWidth, this.mHeight );
        this.mGL.glMatrixMode(GL10.GL_PROJECTION);

        this.mGL.glLoadIdentity();

        this.tTexture = new ArrayList<Draw2DTexture>();
        this.tTexture.clear();

        tex = addTexture( R.drawable.exp );
    }

    public Draw2DTexture addTexture(int resID) {
        int[] textures = new int[1];

        //! create bitmap.
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bmp = BitmapFactory.decodeResource(this.mContext.getResources(),
                resID,
                options);

        //! failed.
        if ( bmp == null )
        {
            Log.e(getClass().toString(), "create bitmap failed");
            return null;
        }

        if ( this.mGL == null )
            return null;

        //! create Texture.
        this.mGL.glGenTextures(1, textures, 0);
        this.mGL.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bmp, 0);
        this.mGL.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        this.mGL.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        this.mGL.glBindTexture(GL10.GL_TEXTURE_2D, 0);

        bmp.recycle();
        if ( textures[0] == 0 ) {
            Log.e(getClass().toString(), "create texture failed\n");
            return null;
        } else {
            //! テクスチャクラス登録.
            Draw2DTexture texture = new Draw2DTexture();
            texture.SetTextureResource(textures[0]);

            this.tTexture.add( texture );
            return texture;
        }
    }

    //! 画面がタッチされたときに呼ばれる.
    public void touched(float x, float y) {
    	//! タッチがあった場所にエフェクト出すのもいいし.
    	//! テクスチャに当たり判定を付けてここでチェックしてもいいかも.
        Log.i(getClass().toString(), String.format("touched! x = %f, y = %f", x, y));
    }

    public void Terminate() {
        if ( tTexture.size() != 0 ) {
            for ( Draw2DTexture texture : tTexture ) {
                texture.Terminate( this.mGL );
            }
            //! ガベージコレクション(いらなかったらコメントアウト).
            System.gc();
        }
    }
}
