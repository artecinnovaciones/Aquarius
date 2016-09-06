package com.artecinnovaciones.aquarius.servicioretrofit.Controlador;

import android.content.Context;
import android.os.AsyncTask;

import com.artecinnovaciones.aquarius.modelodao.ControladorBd.BdController;
import com.artecinnovaciones.aquarius.modelodao.PecesDulce;
import com.artecinnovaciones.aquarius.modelodao.PecesEnfermedades;
import com.artecinnovaciones.aquarius.modelodao.PecesEnfermedadesDao;
import com.artecinnovaciones.aquarius.objetos.Enfermedades;
import com.artecinnovaciones.aquarius.objetos.Peces;
import com.artecinnovaciones.aquarius.servicioretrofit.EnfermedadesPecesService;
import com.artecinnovaciones.aquarius.servicioretrofit.modelresponse.PecesEnfermedadesResponse;
import com.artecinnovaciones.aquarius.sharedpreferenceutils.SharedUtils;

import java.util.List;

/**
 * Created by Geovany.Chin on 19/07/2016.
 */
public class EnfermedadesControlator {

    public static final String TAG = EnfermedadesControlator.class.getSimpleName();

    private EnfermedadesControlator(Context context) {
        mContext = context;
    }

    public static EnfermedadesControlator getInstance(Context mcontext) {
        if (INSTANCE == null) {
            INSTANCE = new EnfermedadesControlator(mcontext.getApplicationContext());
        }
        return INSTANCE;
    }

    public PecesEnfermedadesResponse getListPecesEnfermedades(Context mContext) {
        initWebServiceController();
        PecesEnfermedadesResponse mPecesEnfermedadesResponse = null;
        try {
            mPecesEnfermedadesResponse = mEnfermedadesPecesService.getlistPecesEnfermedades();
            guardarpecesEnfermedadesbd(mPecesEnfermedadesResponse);
            return mPecesEnfermedadesResponse;
        } catch (RuntimeException e) {

            e.printStackTrace();
        }
        return mPecesEnfermedadesResponse;
    }


    public void guardarpecesEnfermedadesbd(PecesEnfermedadesResponse mPecesEnfermedadesResponse) {
        initPecesDao();
        List listPeces = mPecesEnfermedadesDao.queryBuilder().list();
        if (listPeces.size() == 0) {
            int cantidadeimagenesdescargadas = 1;
            for (Enfermedades mEnfermedades : mPecesEnfermedadesResponse.getmListPeces()) {
                descargaImagenesEnfermedades(mEnfermedades, mEnfermedades.getImg(), cantidadeimagenesdescargadas, mPecesEnfermedadesResponse);
                cantidadeimagenesdescargadas++;
            }

        } else {
          //  mPecesEnfermedadesDao.deleteAll();
          //  guardarpecesEnfermedadesbd(mPecesEnfermedadesResponse);

        }
    }

    private void descargaImagenesEnfermedades(final Enfermedades pez, final String image, final int cantidadeimagenesdescargadas, final PecesEnfermedadesResponse mPecesResponse) {

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
                    rutaBd = mEnfermedadesPecesService.getImage(image,mContext);
                } catch (RuntimeException e) {

                    e.printStackTrace();
                }
                return rutaBd;
            }

            @Override
            protected void onPostExecute(String imagen) {
                super.onPostExecute(imagen);
                initPecesDao();

                mPecesEnfermedades = new PecesEnfermedades(null,
                        pez.getNombre(),
                        pez.getSintomas(),
                        pez.getCausas(),
                        pez.getTratamiento(),
                        imagen);

                saveModelClient(mPecesEnfermedades);
                if (mPecesResponse.getmListPeces().size() == cantidadeimagenesdescargadas) {
                    SharedUtils.getInstance(mContext).saveBandObject(1);
                }

            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }


    private void saveModelClient(PecesEnfermedades mPecesEnfermedades) {
        mPecesEnfermedadesDao.insert(mPecesEnfermedades);
    }


    private void initPecesDao() {
        mPecesEnfermedadesDao = BdController.getInstance(mContext).pecesenfermedades();
    }

    public void initWebServiceController() {
        try {
            String url = "http://artecinnovaciones.com";
            mEnfermedadesPecesService = new EnfermedadesPecesService(url);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


    private static EnfermedadesControlator INSTANCE;
    private Context mContext;
    private EnfermedadesPecesService mEnfermedadesPecesService;
    private PecesEnfermedadesDao mPecesEnfermedadesDao;
    private AsyncTask<Void, Integer, String> mpecesImagenesAsyncTask;
    private PecesEnfermedades mPecesEnfermedades;

}
