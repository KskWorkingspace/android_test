package com.example.texture;

import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.texture.opengl.GLSurfaceRendere;
import com.example.texture.sound.BGM;

public class MainActivity extends AppCompatActivity {
    private GLSurfaceView glSurfaceView;
    private BGM bgm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //! openGL.
        GLSurfaceRendere Renderer = new GLSurfaceRendere(this);    //! Render.
        setContentView(R.layout.activity_main);
        glSurfaceView = (GLSurfaceView) findViewById(R.id.surfaceView1);
        glSurfaceView.setZOrderOnTop(true);
        glSurfaceView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        glSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 0, 0);
        glSurfaceView.setRenderer( Renderer );
        //! bgm.
        /*
        bgm = new BGM();
        bgm.setBGM(this, R.raw.test_bgm);
        */
    }

    //! resume.
    protected void onResume() {
        super.onResume();
        glSurfaceView.onResume();
        //bgm.start();
    }

    //! pause.
    protected void onPause() {
        super.onPause();
        glSurfaceView.onPause();
        //bgm.stop();
    }
}
