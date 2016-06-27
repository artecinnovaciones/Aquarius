package com.artecinnovaciones.aquarius.servicioretrofit;


import android.graphics.Bitmap;

import com.artecinnovaciones.aquarius.objetos.Peces;
import com.artecinnovaciones.aquarius.servicioretrofit.WebService.PecesWebService;
import com.artecinnovaciones.aquarius.servicioretrofit.constants.ConstantsService;
import com.artecinnovaciones.aquarius.servicioretrofit.modelresponse.PecesResponse;
import com.artecinnovaciones.aquarius.utilidades.ViewUtil;

import java.io.File;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Geovany.Chin on 23/06/2016.
 */
public class PecesService extends BaseService<PecesWebService> {

    public static final String TAG = PecesWebService.class.getSimpleName();

    public PecesService(String baseUrl) {
        super(baseUrl, PecesWebService.class);

    }

    public PecesResponse getlistPeces() {
        PecesResponse registrationResponse = null;
        try {
            Call<PecesResponse> call = getWebServiceClient().getListPeces();
            Response<PecesResponse> response = call.execute();
            if (response != null && response.errorBody() != null) {

                return registrationResponse;
            }
            registrationResponse = response.body();
        } catch (Exception e) {
            e.getMessage();
        }
        return registrationResponse;
    }

    public Bitmap getImage(String image) {
        Bitmap registrationResponse = null;
        try {

            Call<ResponseBody> call = getWebServiceClient().getImagePeces("/aquarius/uploads/" + image);
            Response<ResponseBody> response = call.execute();

            if (response != null) {
                ViewUtil s = new ViewUtil();
                s.makeFile(response.body(), "IMG_TEMP_"
                        + System.currentTimeMillis() + image);

                return registrationResponse;
            }
            // registrationResponse = response.body();
        } catch (Exception e) {
            e.getMessage();
        }
        return registrationResponse;
    }

    @Deprecated//metodo Asyncrono
    private PecesResponse getResponse(Call<PecesResponse> call) {
        mPecesResponse = null;
        call.enqueue(new Callback<PecesResponse>() {
            @Override
            public void onResponse(Call<PecesResponse> call, Response<PecesResponse> response) {
                if (response != null) {
                    mPecesResponse = response.body();
                }
            }

            @Override
            public void onFailure(Call<PecesResponse> call, Throwable t) {
                t.getMessage();

            }
        });

        return mPecesResponse;
    }

    public PecesResponse mPecesResponse;
}
