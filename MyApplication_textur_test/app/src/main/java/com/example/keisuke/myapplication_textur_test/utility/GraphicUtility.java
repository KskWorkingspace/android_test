package com.example.keisuke.myapplication_textur_test.utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by keisuke on 2016/09/25.
 */
public class GraphicUtility {
    public static Context gContext;
    public static GL10 gGl;
    /*
     * @fn makeFloatBuffer.
     * @brief createFloatBuffer.
     */
    public static final FloatBuffer makeFloatBuffer(float[] floatArray) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(floatArray.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer fb = byteBuffer.asFloatBuffer();
        fb.put(floatArray);
        fb.position(0);
        return fb;
    }

    /*
     * @fn drawSquare.
     * @brief Square drawing functions.
     * @brief We plan to use many times.
     */
    public static final void drawTexture(int textureID,
                                         float x, float y, float width, float height,
                                         float tex_u, float tex_v, float tex_w, float tex_h,
                                         float red, float green, float blue, float alpha ) {

        float[] vertices = {
                -0.5f * width + x, -0.5f * height + y,
                0.5f * width + x, -0.5f * height + y,
                -0.5f * width + x,  0.5f * height + y,
                0.5f * width + x,  0.5f * height + y
        };

        float[] colors = {
                red, green, blue, alpha,
                red, green, blue, alpha,
                red, green, blue, alpha,
                red, green, blue, alpha
        };

        float[] texcoords = {
                tex_u, tex_v + tex_h,
                tex_u + tex_w, tex_v + tex_h,
                tex_u, tex_v,
                tex_u + tex_w, tex_v
        };

        FloatBuffer verticesBuffer = makeFloatBuffer(vertices);
        FloatBuffer colorBuffer = makeFloatBuffer(colors);
        FloatBuffer texcoordBuffer = makeFloatBuffer(texcoords);

        gGl.glEnable(GL10.GL_TEXTURE_2D);

        gGl.glBindTexture(GL10.GL_TEXTURE_2D, textureID);
        gGl.glVertexPointer(2, GL10.GL_FLOAT, 0, verticesBuffer);
        gGl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gGl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
        gGl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gGl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texcoordBuffer);
        gGl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        gGl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

        gGl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        gGl.glDisable(GL10.GL_TEXTURE_2D);
    }

    public static final int loadTexture (int resID) {
        int[] textures = new int[1];

        //! create bitmap.
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bmp = BitmapFactory.decodeResource(GraphicUtility.gContext.getResources(),
                resID,
                options);

        //! failed.
        if ( bmp == null )
        {
            Log.e("ERR", "create bitmap failed");
            return 0;
        }

        //! create Texture.
        gGl.glGenTextures(1, textures, 0);
        gGl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bmp, 0);
        gGl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        gGl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        gGl.glBindTexture(GL10.GL_TEXTURE_2D, 0);

        bmp.recycle();
        return textures[0];
    }
}
