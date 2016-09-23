package com.artecinnovaciones.aquarius.servicioretrofit.Controlador;

import android.content.Context;
import android.os.AsyncTask;

import com.artecinnovaciones.aquarius.modelodao.ControladorBd.BdController;
import com.artecinnovaciones.aquarius.modelodao.PecerasGaleria;
import com.artecinnovaciones.aquarius.modelodao.PecerasGaleriaDao;
import com.artecinnovaciones.aquarius.objetos.Enfermedades;
import com.artecinnovaciones.aquarius.objetos.Galeria;
import com.artecinnovaciones.aquarius.servicioretrofit.GaleriaService;
import com.artecinnovaciones.aquarius.servicioretrofit.modelresponse.GaleriaResponse;
import com.artecinnovaciones.aquarius.servicioretrofit.modelresponse.PecesEnfermedadesResponse;
import com.artecinnovaciones.aquarius.sharedpreferenceutils.SharedUtils;

import java.util.List;

/**
 * Created by LAP-NIDIA on 22/09/2016.
 */
public class GaleriaControlador {

    public static final String TAG = GaleriaControlador.class.getSimpleName();

    private GaleriaControlador(Context context) {
        mContext = context;
    }

    public static GaleriaControlador getInstance(Context mcontext) {
        if (INSTANCE == null) {
            INSTANCE = new GaleriaControlador(mcontext.getApplicationContext());
        }
        return INSTANCE;
    }

    public GaleriaResponse getListGaleria() {
        initWebServiceController();
        GaleriaResponse mGaleriaResponse = null;
        try {
            mGaleriaResponse = mGaleriaService.getlistGaleria();
            guardarGaleria(mGaleriaResponse);
            return mGaleriaResponse;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return mGaleriaResponse;
    }

    public void guardarGaleria(GaleriaResponse mGaleriaResponse) {
        initPecesDao();
        PecerasGaleria mGaleria = null;

            for (Galeria mGa : mGaleriaResponse.getmListPeces()) {
                    mGaleria.setDescripcion(mGa.getDescripcion());
                    mGaleria.setImg(mGa.getImg());
                    saveModelClient(mGaleria);
        }
    }

    private void saveModelClient(PecerasGaleria mPecesGaleria) {
        mPecerasGaleriaDao.insert(mPecesGaleria);
    }

    private void initPecesDao() {
        mPecerasGaleriaDao = BdController.getInstance(mContext).pecerasgaleria();
    }

    public void initWebServiceController() {
        try {
            String url = "http://artecinnovaciones.com";
            mGaleriaService = new GaleriaService(url);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


    private static GaleriaControlador INSTANCE;
    private Context mContext;
    private GaleriaService mGaleriaService;
    private AsyncTask<Void, Integer, String> mpecesImagenesAsyncTask;
    private PecerasGaleriaDao mPecerasGaleriaDao;
    private PecerasGaleria mPecerasGaleria;
}
