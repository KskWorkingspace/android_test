package com.example.keisuke.myapplication_textur_test;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //! openGL.
        GLRenderer renderer = new GLRenderer(this);	//! Render.
        //! create GLSurfaceView.
        GLSurfaceView glSurfaceView = new GLSurfaceView(this);
        //! apply.
        glSurfaceView.setRenderer(renderer);

        setContentView(glSurfaceView);
    }
}
