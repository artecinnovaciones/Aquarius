package com.artecinnovaciones.aquarius.servicioretrofit.Controlador;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.artecinnovaciones.aquarius.modelodao.ControladorBd.BdController;
import com.artecinnovaciones.aquarius.modelodao.PecerasGaleria;
import com.artecinnovaciones.aquarius.modelodao.PecerasGaleriaDao;
import com.artecinnovaciones.aquarius.objetos.Galeria;
import com.artecinnovaciones.aquarius.servicioretrofit.GaleriaService;
import com.artecinnovaciones.aquarius.servicioretrofit.modelresponse.GaleriaResponse;
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
            mGaleriaResponse = mGaleriaService.getlistPecerasGaleria();
            guardarpecerasGaleriabd(mGaleriaResponse);
            return mGaleriaResponse;
        } catch (RuntimeException e) {

            e.printStackTrace();
        }
        return mGaleriaResponse;
    }


    public void guardarpecerasGaleriabd(GaleriaResponse mGaleriaResponse) {
        initPecesDao();
        List listPeces = mPecerasGaleriaDao.queryBuilder().list();
        if (listPeces.size() == 0) {
           // int cantidadeimagenesdescargadas = 1;
            for (Galeria mGaleria : mGaleriaResponse.getmListPeces()) {
               // descargaImagenesGaleria(mGaleria, mGaleria.getImg(), cantidadeimagenesdescargadas, mGaleriaResponse);
                descargaImagenesGaleria(mGaleria, mGaleria.getImg(), mGaleriaResponse);
             //   cantidadeimagenesdescargadas++;
            }

        } else {
            //  mPecerasGaleriaDao.deleteAll();
            //  guardarpecesEnfermedadesbd(mGaleriaResponse);

        }
    }

//    private void descargaImagenesGaleria(final Galeria gal, final String image, final int cantidadeimagenesdescargadas, final GaleriaResponse mGaleriaResponse) {
    private void descargaImagenesGaleria(final Galeria gal, final String image, final GaleriaResponse mGaleriaResponse) {

        mpecesImagenesAsyncTask = new AsyncTask<Void, Integer, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                initWebServiceController();
            }

            @Override
            protected String doInBackground(Void... params) {
                String rutaBd = null;
                try {
                    rutaBd = mGaleriaService.getImage(image,mContext);
                } catch (RuntimeException e) {

                    e.printStackTrace();
                }
                return rutaBd;
            }

            @Override
            protected void onPostExecute(String imagen) {
                super.onPostExecute(imagen);
                initPecesDao();

                mPecerasGaleria = new PecerasGaleria(null,
                        gal.getDescripcion(),
                        imagen);

                saveModelClient(mPecerasGaleria);
               /* if (mGaleriaResponse.getmListPeces().size() == cantidadeimagenesdescargadas) {
                    SharedUtils.getInstance(mContext).saveBandObjectGaleria(1);
                }*/

            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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
