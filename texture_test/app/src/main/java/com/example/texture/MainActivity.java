package com.example.texture;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.texture.opengl.GLSurface;
import com.example.texture.opengl.GLSurfaceRendere;
import com.example.texture.sound.BGM;
import com.example.texture.texture.Draw2DTexture;

public class MainActivity extends AppCompatActivity {
    private GLSurface glSurfaceView;
    private BGM bgm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //! openGL.
        GLSurfaceRendere Renderer = new GLSurfaceRendere(this);    //! Render.
        /*
        //! create GLSurfaceView.
        glSurfaceView = new GLSurfaceView(this);
        //! apply.
        glSurfaceView.setRenderer( Renderer );
        setContentView( glSurfaceView );
        */
        setContentView(R.layout.activity_main);
        glSurfaceView = (GLSurface) findViewById(R.id.surfaceView1);
        glSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 8, 8);
        glSurfaceView.setRenderer( Renderer );
        //! bgm.
        bgm = new BGM();
        bgm.setBGM(this, R.raw.test_bgm);
    }

    //! resume.
    protected void onResume() {
        super.onResume();
        glSurfaceView.onResume();
        bgm.start();
    }

    //! pause.
    protected void onPause() {
        super.onPause();
        glSurfaceView.onPause();
        bgm.stop();
    }

    @Override
    public boolean dispatchKeyEvent( KeyEvent event ) {
        //! キーが押された.
        if ( event.getAction() == KeyEvent.ACTION_DOWN ) {
            switch ( event.getKeyCode() ) {
                case KeyEvent.KEYCODE_BACK:
                //! Backボタン.
                    return false;
                default:
                //! NOP.
            }
        }
        return super.dispatchKeyEvent(event);
    }
}
