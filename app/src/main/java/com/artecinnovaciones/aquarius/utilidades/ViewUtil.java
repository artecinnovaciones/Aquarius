package com.artecinnovaciones.aquarius.utilidades;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
  /*  public boolean DownloadandSaveImage(ResponseBody body, String image) {
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
    } */

    public String makeFile(Context context, ResponseBody body, String image) {
      /*  File fileDir = new File(TEMP_DIRECTORY_PATH);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        DownloadandSaveImage(body,image);*/
        Bitmap   bm = BitmapFactory.decodeStream(body.byteStream());
        return  guardarImagen(context,image,bm);
    }
    public String guardarImagen (Context context, String nombre, Bitmap imagen){
        ContextWrapper cw = new ContextWrapper(context);
        File dirImages = cw.getDir("Aquarius", Context.MODE_PRIVATE);
        File myPath = new File(dirImages, nombre);

        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(myPath);
            imagen.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return myPath.getAbsolutePath();
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

    public void guardarImg(Context context, Bitmap ImageToSave) {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-­ss");
        String formattedDate = df.format(c.getTime());

        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Aquarius";
        File dir = new File(file_path);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir, "Aquarius_" + formattedDate + ".png");

        try {
            FileOutputStream fOut = new FileOutputStream(file);

            ImageToSave.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            Toast.makeText(context, "Imágen guardada en la galería.", Toast.LENGTH_SHORT).show();
        }

        catch(FileNotFoundException e) {
            Toast.makeText(context, "¡Error al guardar la imágen!", Toast.LENGTH_SHORT).show();
        }
        catch(IOException e) {
            Toast.makeText(context, "¡Error al guardar la imágen!", Toast.LENGTH_SHORT).show();
        }

    }

    public void requestNewInterstitial(InterstitialAd mInterstitialAd) {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    //public static final String TEMP_DIRECTORY_PATH = Environment.getExternalStorageDirectory()+ "/Peces/Temp/";

}
