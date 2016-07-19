package com.artecinnovaciones.aquarius.sharedpreferenceutils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Geovany.Chin on 08/07/2016.
 */
public class SharedUtils {
    public static final String TAG = SharedUtils.class
            .getSimpleName();

    public static SharedUtils getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SharedUtils(context);
        }
        return INSTANCE;
    }

    public void saveBandObject(int object) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(VALOR, object);
        editor.commit();
    }
    public void saveBandObjectEnfermedades(int object) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(ENFERMEDADES, object);
        editor.commit();
    }
    public int getIfDowload() {

        return mPreferences.getInt(VALOR, 0);
    }

    public void getclear() {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.remove(VALOR).commit();
    }

    private SharedUtils(Context context) {
        mContext = context;
        initPrefs();
    }

    private void initPrefs() {
        mPreferences = mContext.getSharedPreferences(UTILS_PREFS,
                Context.MODE_PRIVATE);
    }

    private static SharedUtils INSTANCE;
    private Context mContext;
    private static final String UTILS_PREFS = TAG + "UtilsPreference";
    private static final String VALOR = "valor";
    private static final String ENFERMEDADES = "valor";
    private SharedPreferences mPreferences;

}
