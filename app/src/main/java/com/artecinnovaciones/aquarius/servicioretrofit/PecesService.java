package com.artecinnovaciones.aquarius.servicioretrofit;

import com.artecinnovaciones.aquarius.servicioretrofit.WebService.PecesWebService;
import com.artecinnovaciones.aquarius.servicioretrofit.modelresponse.PecesResponse;

import java.io.IOException;

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
        Call<PecesResponse> result = null;
        /*try {
           // ss = (PecesResponse) getWebServiceClient().getListPeces();

           result = getWebServiceClient().getListPeces();

        } catch (Exception e) {
            e.getMessage();
        }*/


        result = getWebServiceClient().getListPeces();
        result.enqueue(new Callback<PecesResponse>() {

            @Override
            public void onResponse(Call<PecesResponse> call, Response<PecesResponse> response) {

                if (response != null) {
                    PecesResponse = response;
                    System.out.print(response);
                }

            }


            @Override
            public void onFailure(Call<PecesResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return null;

    }

    public Response PecesResponse;
}
