package com.artecinnovaciones.aquarius.servicioretrofit.WebService;

import com.artecinnovaciones.aquarius.servicioretrofit.constants.ConstantsService;
import com.artecinnovaciones.aquarius.servicioretrofit.modelresponse.CompararBd;
import com.artecinnovaciones.aquarius.servicioretrofit.modelresponse.PecesEnfermedadesResponse;
import com.artecinnovaciones.aquarius.servicioretrofit.modelresponse.PecesResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Geovany.Chin on 23/06/2016.
 */
public interface PecesWebService {


    @GET(ConstantsService.GET.VALIDATE_LOGIN)
    Call<PecesResponse> getListPeces();

    @GET
    Call<ResponseBody> getImagePeces(@Url String fileUrl);

    @GET(ConstantsService.GET.PECESENFERMEDADES)
    Call<PecesEnfermedadesResponse> getListPecesEnfermedades();

    @GET(ConstantsService.GET.COMPARARBD)
    Call<CompararBd> getCompara();

}

