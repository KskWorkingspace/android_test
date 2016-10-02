package com.example.texture;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.texture.opengl.GLSurfaceRendere;
import com.example.texture.texture.Draw2DTexture;

public class MainActivity extends AppCompatActivity {
    private GLSurfaceView glSurfaceView;

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
        glSurfaceView = (GLSurfaceView) findViewById(R.id.surfaceView1);
        glSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 8, 8);
        glSurfaceView.setRenderer( Renderer );
    }

    //! resume.
    protected void onResume() {
        super.onResume();
        glSurfaceView.onResume();
    }

    //! pause.
    protected void onPause() {
        super.onPause();
        glSurfaceView.onPause();
    }
}
