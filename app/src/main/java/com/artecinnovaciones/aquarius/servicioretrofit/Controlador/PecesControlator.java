package com.artecinnovaciones.aquarius.servicioretrofit.Controlador;

import android.content.Context;

import com.artecinnovaciones.aquarius.modelodao.ControladorBd.BdController;
import com.artecinnovaciones.aquarius.modelodao.PecesDulce;
import com.artecinnovaciones.aquarius.modelodao.PecesDulceDao;
import com.artecinnovaciones.aquarius.objetos.Peces;
import com.artecinnovaciones.aquarius.servicioretrofit.PecesService;
import com.artecinnovaciones.aquarius.servicioretrofit.modelresponse.PecesResponse;

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

    public PecesResponse getListPeces() {
        initWebServiceController();
        PecesResponse mPecesResponse = null;
        try {
            mPecesResponse = mPecesService.getlistPeces();
            guardarpecesbd(mPecesResponse);
            return mPecesResponse;


        } catch (RuntimeException e) {

            e.printStackTrace();
        }
        return mPecesResponse;
    }


    private void guardarpecesbd(PecesResponse mPecesResponse) {
        initPecesDao();
        PecesDulce mPecesDulce = null;
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
                        mPeces.getImagen());

                saveModelClient(mPecesDulce);
            }
        } else {
            mPecesDulceDao.deleteAll();
           guardarpecesbd(mPecesResponse);
        }
    }
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


}
