package com.artecinnovaciones.aquarius.servicioretrofit.Controlador;

import android.content.Context;

import com.artecinnovaciones.aquarius.servicioretrofit.PecesService;
import com.artecinnovaciones.aquarius.servicioretrofit.modelresponse.PecesResponse;

/**
 * Created by Geovany.Chin on 23/06/2016.
 */
public class PecesControlator {
    public static final String TAG = PecesControlator.class.getSimpleName();

    private PecesControlator(Context context) {
        mContext = context;
    }

    public static PecesControlator getInstance(Context mcontext) {
        if (INSTANCE == null) {
            INSTANCE = new PecesControlator(mcontext.getApplicationContext());
        }
        return INSTANCE;
    }

    public PecesResponse getListPeces() {
        initWebServiceController();
        try {

             mPecesService.getlistPeces();


                return null;


        } catch (RuntimeException e) {

            e.printStackTrace();
        }
        return null;
    }

    public void initWebServiceController() {
        try {
            String url = "http://artecinnovaciones.com/";
            mPecesService = new PecesService(url);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private static PecesControlator INSTANCE;
    private Context mContext;
    PecesService mPecesService;

}
