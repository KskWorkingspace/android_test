package com.example.keisuke.myapplication_textur_test.manager;

import android.util.Log;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.example.keisuke.myapplication_textur_test.utility.GraphicUtility;

/**
 * Created by keisuke on 2016/09/25.
 */
public class TextureManager {
    //! To manage the data in bulk.
    private static Map<Integer, Integer> mTextures = new Hashtable<Integer, Integer>();

    /*
     * @fn addTexture.
     * @param[in] int resID texture resource id.
     * @retval int texture id.
     * @brief add texture to create after the list.
     */
    public static final int addTexture(int resID) {

        int ret = 0;
        if (!mTextures.containsKey(resID))
        {
            int texID = GraphicUtility.loadTexture(resID);
            if (texID == 0)
                ret = 0;
            else {
                mTextures.put(resID, texID);
            }
            ret = texID;
        }
        return ret;
    }

    /*
     * @fn deleteTexture.
     * @param[in] int resID texture resource id.
     * @brief remove the texture from the list.
     */
    public static final void deleteTexture(int resID) {

        if ( GraphicUtility.gGl == null || resID == 0) {
            Log.e("ERR", "texture delete failed\n");
            return;
        }

        if (mTextures.containsKey(resID)) {
            int[] texture = new int[1];

            texture[0] = mTextures.get(resID);

            GraphicUtility.gGl.glDeleteTextures(1, texture, 0);

            mTextures.remove(resID);
        }
    }

    /*
     * @fn deleteTextureArry.
     * @param[in] int[] resID texture resource id array.
     * @brief remove summarizes the texture from the list.
     */
    public static final void deleteTextureArry(int[] resID) {

        if ( GraphicUtility.gGl == null || resID == null) {
            Log.e("ERR", "texture delete arry failed\n");
            return;
        }
        for (Integer key : resID) {
            deleteTexture(key);
        }
    }

    /*
     * @fn deleteTextureAll.
     * @brief remove all the texture from the list.
     */
    public static final void deleteTextureAll() {

        if ( GraphicUtility.gGl == null ) {
            Log.e("ERR", "texture delete all failed\n");
            return;
        }

        List<Integer> keys = new ArrayList<Integer>(mTextures.keySet());
        for (Integer key : keys) {
            deleteTexture(key);
        }
    }
}
