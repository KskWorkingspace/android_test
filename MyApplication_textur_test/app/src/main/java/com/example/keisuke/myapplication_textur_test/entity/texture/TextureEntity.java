package com.example.keisuke.myapplication_textur_test.entity.texture;

import android.util.Log;

import com.example.keisuke.myapplication_textur_test.utility.GraphicUtility;
import com.example.keisuke.myapplication_textur_test.manager.TextureManager;
/**
 * Created by keisuke on 2016/09/25.
 */
public class TextureEntity {
    //! texture id.
    private int mSampleTexture;
    //! position.
    private  float mPox_x, mPos_y;
    //! scale.
    private float mScale_x, mScale_y;
    //! angle.
    private float mAngle;
    public TextureEntity() {
        this.init();
    }

    public TextureEntity( int in_redID ) {
        this.init();

        this.SetTextureResource( in_redID );
    }

    public void init() {
        this.mSampleTexture = 0;
        this.mPox_x = this.mPos_y = 0.0f;
        this.mScale_x = this.mScale_y = 0.0f;
    }

    public void SetTextureResource( int in_redID ) {
        if ( in_redID != 0 )
            this.mSampleTexture = TextureManager.addTexture(in_redID);
        else
            Log.e( "TextureEntity", "in resource id Illegal value\n" );

        //! failed.
        if ( this.mSampleTexture == 0 )
            Log.e( "TextureEntity", "initialize texture data\n" );
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

    public void Draw() {
        //! Matrix transformation.
        GraphicUtility.gGl.glPushMatrix();
        {
            GraphicUtility.gGl.glTranslatef(this.mPox_x, this.mPos_y, 0.0f);
            GraphicUtility.gGl.glRotatef(this.mAngle, 0.0f, 0.0f, 1.0f);
            GraphicUtility.gGl.glScalef(this.mScale_x, this.mScale_y, 1.0f);

            //! square polygon draw.
            GraphicUtility.drawTexture(this.mSampleTexture,
                    0, 0, 1.0f, 1.0f,
                    0.0f, 0.0f, 1.0f, 1.0f,
                    1.0f, 1.0f, 1.0f, 1.0f
            );
        }
        GraphicUtility.gGl.glPopMatrix();
    }

    public void Terminate() {
        TextureManager.deleteTexture(this.mSampleTexture);
    }
}
