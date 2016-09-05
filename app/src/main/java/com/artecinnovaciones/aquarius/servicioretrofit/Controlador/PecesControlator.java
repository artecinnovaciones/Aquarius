package com.artecinnovaciones.aquarius.servicioretrofit.Controlador;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.artecinnovaciones.aquarius.modelodao.ControladorBd.BdController;
import com.artecinnovaciones.aquarius.modelodao.PecesDulce;
import com.artecinnovaciones.aquarius.modelodao.PecesDulceDao;
import com.artecinnovaciones.aquarius.objetos.Peces;
import com.artecinnovaciones.aquarius.servicioretrofit.PecesService;
import com.artecinnovaciones.aquarius.servicioretrofit.modelresponse.CompararBd;
import com.artecinnovaciones.aquarius.servicioretrofit.modelresponse.PecesEnfermedadesResponse;
import com.artecinnovaciones.aquarius.servicioretrofit.modelresponse.PecesResponse;
import com.artecinnovaciones.aquarius.sharedpreferenceutils.SharedUtils;

import java.util.ArrayList;
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

    public CompararBd getCompararBd(Context mContext) {
        CompararBd valor = null;
        initPecesDao();
        initWebServiceController();
        try {
            List listPeces = mPecesDulceDao.queryBuilder().list();
            System.out.print(listPeces);
        }catch (RuntimeException e) {
//            Toast.makeText(mContext,e.getMessage(),Toast.LENGTH_LONG).show();
        }
       /* try {
            valor = mPecesService.getCompararBd();
            valor.setBdinterna(listPeces.size());
            return valor;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        valor.setBdinterna(listPeces.size());*/
        return valor;
    }


    public PecesResponse getListPeces() {
        PecesResponse mPecesResponse = null;
        try {
            descargarEnfermedades();
            mPecesResponse = mPecesService.getlistPeces(mContext);
            return mPecesResponse;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return mPecesResponse;
    }

    private void descargarEnfermedades() {
        mpecesEnfermedadesAsynkTask = new AsyncTask<Void, Integer, PecesEnfermedadesResponse>() {
            @Override
            protected PecesEnfermedadesResponse doInBackground(Void... params) {
                return EnfermedadesControlator.getInstance(mContext).getListPecesEnfermedades(mContext);
            }
        }.execute();
    }


    public void guardarpecesbd(PecesResponse mPecesResponse) {
        initPecesDao();
        try {
            List listPeces = mPecesDulceDao.queryBuilder().list();

        if (listPeces.size() == 0) {
            int cantidadeimagenesdescargadas = 1;
            for (Peces mPeces : mPecesResponse.getmListPeces()) {
                Toast.makeText(mContext,mPeces.getImagen()+" "+  cantidadeimagenesdescargadas,Toast.LENGTH_LONG).show();
                descargaImagenes(mPeces, mPeces.getImagen(), cantidadeimagenesdescargadas, mPecesResponse);
                cantidadeimagenesdescargadas++;
            }

        } else {
            //  mPecesDulceDao.deleteAll();
            //    guardarpecesbd(mPecesResponse);
        }
        }catch (Exception e){

        }
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
                    rutaBd = mPecesService.getImage(image,mContext);
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
                        pez.getTipo(),
                        imagen);

                saveModelClient(mPecesDulce);
                if (mPecesResponse.getmListPeces().size() == cantidadeimagenesdescargadas) {
                    SharedUtils.getInstance(mContext).saveBandObjectEnfermedades(1);
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
        SharedUtils.getInstance(mContext).ultimoIdBd(mPeces.getId());
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
    private AsyncTask<Void, Integer, PecesEnfermedadesResponse> mpecesEnfermedadesAsynkTask;
    private AsyncTask<Void, Integer, CompararBd> mpecesCompararBd;
    private PecesDulce mPecesDulce;
    int variable = 0;
}
