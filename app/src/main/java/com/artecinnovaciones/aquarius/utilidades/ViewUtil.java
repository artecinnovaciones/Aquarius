package com.artecinnovaciones.aquarius.utilidades;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;
import android.view.View;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by LAP-NIDIA on 20/05/2016.
 */
public class ViewUtil {
    @SuppressWarnings("unchecked")
    public static <T extends View> T findViewById(View view, int resource) {
        return (T) view.findViewById(resource);
    }

    @SuppressWarnings("unchecked")
    public static <T extends View> T findViewById(Activity activity, int resource) {
        return (T) activity.findViewById(resource);
    }


    public boolean DownloadandSaveImage(ResponseBody body, String image) {
        try {

            InputStream in = null;
            FileOutputStream out = null;

            try {
                in = body.byteStream();
                out = new FileOutputStream(TEMP_DIRECTORY_PATH + image);
                int c;

                while ((c = in.read()) != -1) {
                    out.write(c);
                }
            } catch (IOException e) {

                return false;
            } finally {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }
            return true;

        } catch (IOException e) {
            return false;
        }
    }


    public void makeFile(ResponseBody body, String image) {
        File fileDir = new File(TEMP_DIRECTORY_PATH);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        DownloadandSaveImage(body,image);

    }

    public static final Boolean validateDataNetwork(Activity activity) {
        ConnectivityManager connManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mMobile = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (mWifi.isConnected() || mMobile.isConnected()) {
            return true;
        } else {
            return false;
        }
    }


    public static final String TEMP_DIRECTORY_PATH = Environment.getExternalStorageDirectory()+ "/Peces/Temp/";


}
