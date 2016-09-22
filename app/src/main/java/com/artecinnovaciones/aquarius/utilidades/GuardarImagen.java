package com.artecinnovaciones.aquarius.utilidades;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.ResponseBody;

/**
 * Created by LAP-NIDIA on 22/09/2016.
 */
public class GuardarImagen {

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
}
