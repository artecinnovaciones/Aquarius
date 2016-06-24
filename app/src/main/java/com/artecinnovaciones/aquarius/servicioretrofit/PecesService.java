package com.artecinnovaciones.aquarius.servicioretrofit;


import com.artecinnovaciones.aquarius.objetos.Peces;
import com.artecinnovaciones.aquarius.servicioretrofit.WebService.PecesWebService;
import com.artecinnovaciones.aquarius.servicioretrofit.modelresponse.PecesResponse;

import java.util.List;

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
        Call<PecesResponse> call = null;

        call = getWebServiceClient().getListPeces();

        return getResponse(call);
    }

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
