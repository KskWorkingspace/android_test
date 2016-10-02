package com.example.texture.texture;

import android.opengl.GLSurfaceView;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by keisuke on 2016/10/02.
 */
public class Draw2DTexture {
    /**
     * Created by keisuke on 2016/09/25.
     */
    //! texture id.
    private int mSampleTexture[];
    //! position.
    private  float mPox_x, mPos_y;
    //! scale.
    private float mScale_x, mScale_y;
    //! angle.
    private float mAngle;
    //! color.
    private float mRed, mGreen, mBlue, mAlpha;

    public Draw2DTexture() {
        this.init();
    }

    public Draw2DTexture( int in_redID ) {
        this.init();
        this.SetTextureResource( in_redID );
    }

    public void init() {
        this.mSampleTexture = new int[1];
        this.mSampleTexture[0] = 0;
        this.mPox_x = this.mPos_y = 0.0f;
        this.mScale_x = this.mScale_y = 1.0f;
        mRed = mGreen = mBlue = mAlpha = 1.0f;
    }

    public void SetTextureResource( int in_texID ) {
        if ( in_texID != 0 && this.mSampleTexture[0] == 0 )
            this.mSampleTexture[0] = in_texID;
        else
            Log.e( "TextureEntity", "in resource id Illegal value\n" );
    }

    public void SetPos( float in_x, float in_y ) {
        this.mPox_x = in_x;
        this.mPos_y = in_y;
    }

    public void SetScale( float in_x, float in_y ) {
        this.mScale_x = in_x;
        this.mScale_y = in_y;
    }

    public void SetAngle( float in_angle ) {
        this.mAngle = in_angle;
        //! limit.
        if ( !( 0 <= this.mAngle && this.mAngle < 360 ) ) {
            float ang = this.mAngle % 360;
            if ( ang > 0 )
                this.mAngle = ang;
            else
                this.mAngle += ang;
        }
    }

    public void AddAngle( float in_add_ang ) {
        this.mAngle += in_add_ang;
        //! limit.
        if ( !( 0 <= this.mAngle && this.mAngle < 360 ) ) {
            float ang = this.mAngle % 360;
            if ( ang > 0 )
                this.mAngle = ang;
            else
                this.mAngle += ang;
        }
    }

    public void Draw(GL10 gl) {
        //! Matrix transformation.
        gl.glPushMatrix();
        {
            gl.glTranslatef(this.mPox_x, this.mPos_y, 0.0f);
            gl.glRotatef(this.mAngle, 0.0f, 0.0f, 1.0f);
            gl.glScalef(this.mScale_x, this.mScale_y, 1.0f);
            //! square polygon draw.
            float[] vertices = {
                    -0.5f, -0.5f,
                    0.5f, -0.5f,
                    -0.5f,  0.5f,
                    0.5f,  0.5f
            };

            float[] colors = {
                    this.mRed, this.mGreen, this.mBlue, this.mAlpha,
                    this.mRed, this.mGreen, this.mBlue, this.mAlpha,
                    this.mRed, this.mGreen, this.mBlue, this.mAlpha,
                    this.mRed, this.mGreen, this.mBlue, this.mAlpha
            };

            float[] texcoords = {
                    0.0f, 1.0f,
                    1.0f, 1.0f,
                    0.0f, 0.0f,
                    1.0f, 0.0f
            };

            FloatBuffer verticesBuffer = makeFloatBuffer(vertices);
            FloatBuffer colorBuffer = makeFloatBuffer(colors);
            FloatBuffer texcoordBuffer = makeFloatBuffer(texcoords);

            gl.glEnable(GL10.GL_TEXTURE_2D);
            gl.glBindTexture( GL10.GL_TEXTURE_2D, this.mSampleTexture[0] );
            gl.glVertexPointer( 2, GL10.GL_FLOAT, 0, verticesBuffer );
            gl.glEnableClientState( GL10.GL_VERTEX_ARRAY );
            gl.glColorPointer( 4, GL10.GL_FLOAT, 0, colorBuffer );
            gl.glEnableClientState( GL10.GL_COLOR_ARRAY );
            gl.glTexCoordPointer( 2, GL10.GL_FLOAT, 0, texcoordBuffer );
            gl.glEnableClientState( GL10.GL_TEXTURE_COORD_ARRAY );

            gl.glDrawArrays( GL10.GL_TRIANGLE_STRIP, 0, 4 );

            gl.glDisableClientState( GL10.GL_TEXTURE_COORD_ARRAY) ;

            gl.glDisable( GL10.GL_TEXTURE_2D );
        }
        gl.glPopMatrix();
    }

    public void Terminate( GL10 gl ) {
        if ( gl == null ) {
            Log.e("ERR", "texture delete failed\n");
            return;
        }
        gl.glDeleteTextures(1, this.mSampleTexture, 0 );
    }

    /*
     * @fn makeFloatBuffer.
     * @brief createFloatBuffer.
     */
    private static final FloatBuffer makeFloatBuffer(float[] floatArray) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(floatArray.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer fb = byteBuffer.asFloatBuffer();
        fb.put(floatArray);
        fb.position(0);
        return fb;
    }
}
