package com.example.texture.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

/**
 * Created by keisuke on 2016/10/15.
 */
public class GLSurface  extends GLSurfaceView {

    //画面サイズ
    private float mWidth;
    private float mHeight;

    //MyRendererを保持する
    private GLSurfaceRendere mMyRenderer;

    public GLSurface(Context context) {
        super(context);
        setFocusable(true);//タッチイベントが取得できるようにする
    }

    @Override
    public void setRenderer(Renderer renderer) {
        super.setRenderer(renderer);
        this.mMyRenderer = (GLSurfaceRendere) renderer;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        super.surfaceChanged(holder, format, w, h);
        this.mWidth = w;
        this.mHeight = h;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int count = event.getPointerCount();

        for ( int ii = 0; ii < count; ii++ ) {
            float x = (event.getX(ii) / (float) mWidth) * 2.0f - 1.0f;
            float y = (event.getY(ii) / (float) mHeight) * -3.0f + 1.5f;
            mMyRenderer.touched(x, y);
        }

        return true;
    }
}