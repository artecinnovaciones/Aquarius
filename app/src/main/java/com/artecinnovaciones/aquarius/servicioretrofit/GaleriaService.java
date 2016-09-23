package com.artecinnovaciones.aquarius.servicioretrofit;

import android.content.Context;

import com.artecinnovaciones.aquarius.servicioretrofit.Controlador.GaleriaControlador;
import com.artecinnovaciones.aquarius.servicioretrofit.WebService.PecesWebService;
import com.artecinnovaciones.aquarius.servicioretrofit.modelresponse.GaleriaResponse;
import com.artecinnovaciones.aquarius.utilidades.ViewUtil;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LAP-NIDIA on 22/09/2016.
 */
public class GaleriaService extends BaseService<PecesWebService> {

    public static final String TAG = PecesWebService.class.getSimpleName();

    public GaleriaService(String baseUrl) {
        super(baseUrl, PecesWebService.class);
    }
    public GaleriaResponse getlistGaleria() {
        mGaleriaResponse = null;
        try {
            Call<GaleriaResponse> call = getWebServiceClient().getListGaleria();
            Response<GaleriaResponse> response = call.execute();
            mGaleriaResponse = response.body();
        } catch (Exception e) {
            e.getMessage();
        }
        return mGaleriaResponse;
    }
    public GaleriaResponse mGaleriaResponse;
}
