package com.artecinnovaciones.aquarius.servicioretrofit;


import com.artecinnovaciones.aquarius.servicioretrofit.WebService.PecesWebService;
import com.artecinnovaciones.aquarius.servicioretrofit.modelresponse.PecesResponse;

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

    public void getlistPeces() {


        getWebServiceClient().getListPeces(new Callback<PecesResponse>() {
            @Override
            public void onResponse(Call<PecesResponse> call, Response<PecesResponse> response) {
                if (call != null && response != null) {

                }
            }

            @Override
            public void onFailure(Call<PecesResponse> call, Throwable t) {
                t.getMessage();
            }
        });

    }

    public PecesResponse PecesResponse;
}
