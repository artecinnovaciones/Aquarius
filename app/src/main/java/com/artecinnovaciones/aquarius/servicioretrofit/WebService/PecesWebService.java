package com.artecinnovaciones.aquarius.servicioretrofit.WebService;

import android.graphics.Bitmap;

import com.artecinnovaciones.aquarius.objetos.Peces;
import com.artecinnovaciones.aquarius.servicioretrofit.constants.ConstantsService;
import com.artecinnovaciones.aquarius.servicioretrofit.modelresponse.PecesResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Geovany.Chin on 23/06/2016.
 */
public interface PecesWebService {

    @GET(ConstantsService.GET.VALIDATE_LOGIN)
    Call<PecesResponse> getListPeces();


    @GET(ConstantsService.GET.IMAGEDESCARGADA)
    Call<ResponseBody> getImagePeces(@Path("image") String image);
}

