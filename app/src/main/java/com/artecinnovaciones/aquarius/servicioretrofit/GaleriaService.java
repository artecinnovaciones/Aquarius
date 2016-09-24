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
  /*  @Deprecated
    public GaleriaResponse getlistPecesEnfermedades1(final Context mContext) {
        mGaleriaResponse = null;

        Call<GaleriaResponse> call = getWebServiceClient().getListGaleria();
        call.enqueue(new Callback<GaleriaResponse>() {
            @Override
            public void onResponse(Call<GaleriaResponse> call, Response<GaleriaResponse> response) {
                mGaleriaResponse = response.body();

                GaleriaControlador.getInstance(mContext).guardarpecesEnfermedadesbd(mGaleriaResponse);
            }

            @Override
            public void onFailure(Call<GaleriaResponse> call, Throwable t) {

            }
        });
        return mGaleriaResponse;
    }*/

    public GaleriaResponse getlistPecerasGaleria() {
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
    public String getImage(String image,Context mcontext) {
        String registrationImageBd = null;
        try {

            Call<ResponseBody> call = getWebServiceClient().getImagePeces("/aquarius/uploads/" + image);
            Response<ResponseBody> response = call.execute();

            if (response != null) {
                registrationImageBd = new ViewUtil().makeFile(mcontext, response.body(), image);
                return registrationImageBd;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return registrationImageBd;
    }

    @Deprecated//metodo Asyncrono
    private GaleriaResponse getResponse(Call<GaleriaResponse> call) {
        mGaleriaResponse = null;
        call.enqueue(new Callback<GaleriaResponse>() {
            @Override
            public void onResponse(Call<GaleriaResponse> call, Response<GaleriaResponse> response) {
                if (response != null) {
                    mGaleriaResponse = response.body();
                }
            }

            @Override
            public void onFailure(Call<GaleriaResponse> call, Throwable t) {
                t.getMessage();

            }
        });

        return mGaleriaResponse;
    }

    public GaleriaResponse mGaleriaResponse;
}
