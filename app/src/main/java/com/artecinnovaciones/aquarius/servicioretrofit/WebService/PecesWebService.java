package com.artecinnovaciones.aquarius.servicioretrofit.WebService;

import com.artecinnovaciones.aquarius.servicioretrofit.constants.ConstantsService;
import com.artecinnovaciones.aquarius.servicioretrofit.modelresponse.PecesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Geovany.Chin on 23/06/2016.
 */
public interface PecesWebService {

    @GET(ConstantsService.GET.VALIDATE_LOGIN)
    PecesResponse getListPeces();
}
