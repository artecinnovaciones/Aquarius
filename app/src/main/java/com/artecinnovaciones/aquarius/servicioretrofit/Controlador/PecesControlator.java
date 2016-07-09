package com.artecinnovaciones.aquarius.servicioretrofit.Controlador;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.artecinnovaciones.aquarius.modelodao.ControladorBd.BdController;
import com.artecinnovaciones.aquarius.modelodao.PecesDulce;
import com.artecinnovaciones.aquarius.modelodao.PecesDulceDao;
import com.artecinnovaciones.aquarius.objetos.Peces;
import com.artecinnovaciones.aquarius.servicioretrofit.PecesService;
import com.artecinnovaciones.aquarius.servicioretrofit.constants.ConstantsService;
import com.artecinnovaciones.aquarius.servicioretrofit.modelresponse.PecesResponse;
import com.artecinnovaciones.aquarius.sharedpreferenceutils.SharedUtils;

import java.util.List;


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

    public PecesResponse getListPeces(Context mContext) {
        initWebServiceController();
        PecesResponse mPecesResponse = null;
        try {
            mPecesResponse = mPecesService.getlistPeces(mContext);
            //  guardarpecesbd(mPecesResponse);
            return mPecesResponse;


        } catch (RuntimeException e) {

            e.printStackTrace();
        }
        return mPecesResponse;
    }


    public void guardarpecesbd(PecesResponse mPecesResponse) {
        initPecesDao();
        List listPeces = mPecesDulceDao.queryBuilder().list();
        if (listPeces.size() == 0) {
            int cantidadeimagenesdescargadas = 1;
            for (Peces mPeces : mPecesResponse.getmListPeces()) {
                descargaImagenes(mPeces, mPeces.getImagen(), cantidadeimagenesdescargadas, mPecesResponse);
                cantidadeimagenesdescargadas++;
            }

        } else {
            mPecesDulceDao.deleteAll();
            guardarpecesbd(mPecesResponse);

        }
      /*  initPecesDao();
        mPecesDulce = null;
        List listPeces = mPecesDulceDao.queryBuilder().list();
        if (listPeces.size() == 0) {
            for (Peces mPeces : mPecesResponse.getmListPeces()) {

                mPecesDulce = new PecesDulce(null,
                        mPeces.getNombreCientifico(),
                        mPeces.getNombreComun(),
                        mPeces.getInformacion(),
                        mPeces.getCuidados(),
                        mPeces.getAlimentacion(),
                        mPeces.getMasBuscado(),
                        descargaImagenes(mPeces, mPeces.getImagen()));

                saveModelClient(mPecesDulce);

            }
            SharedUtils.getInstance(mContext).saveBandObject(1);
        } else {
            mPecesDulceDao.deleteAll();
            guardarpecesbd(mPecesResponse);
        }*/
    }

    private void descargaImagenes(final Peces pez, final String image, final int cantidadeimagenesdescargadas, final PecesResponse mPecesResponse) {

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
                    rutaBd = mPecesService.getImage(image);
                } catch (RuntimeException e) {

                    e.printStackTrace();
                }
                return rutaBd;
            }

            @Override
            protected void onPostExecute(String imagen) {
                super.onPostExecute(imagen);
                initPecesDao();

                mPecesDulce = new PecesDulce(null,
                        pez.getNombreCientifico(),
                        pez.getNombreComun(),
                        pez.getInformacion(),
                        pez.getCuidados(),
                        pez.getAlimentacion(),
                        pez.getMasBuscado(),
                        imagen);

                saveModelClient(mPecesDulce);
                if (mPecesResponse.getmListPeces().size() == cantidadeimagenesdescargadas) {
                    SharedUtils.getInstance(mContext).saveBandObject(1);
                }

            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

   /* private String descargadeimagen(String image) {
        initWebServiceController();
        String rutaBd = null;
        try {
            rutaBd = mPecesService.getImage(image);

            return rutaBd.toString();


        } catch (RuntimeException e) {

            e.printStackTrace();
        }
        return rutaBd;
    }*/

    private void saveModelClient(PecesDulce mPeces) {
        mPecesDulceDao.insert(mPeces);
    }


    private void initPecesDao() {
        mPecesDulceDao = BdController.getInstance(mContext).pecesdulce();
    }

    public void initWebServiceController() {
        try {
            String url = "http://artecinnovaciones.com";
            mPecesService = new PecesService(url);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


    private static PecesControlator INSTANCE;
    private Context mContext;
    private PecesService mPecesService;
    private PecesDulceDao mPecesDulceDao;
    private AsyncTask<Void, Integer, String> mpecesImagenesAsyncTask;
    private PecesDulce mPecesDulce;


}
