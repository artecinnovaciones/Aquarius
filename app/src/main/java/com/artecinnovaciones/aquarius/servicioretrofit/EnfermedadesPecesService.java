package com.artecinnovaciones.aquarius.servicioretrofit;

import android.content.Context;

import com.artecinnovaciones.aquarius.servicioretrofit.Controlador.EnfermedadesControlator;
import com.artecinnovaciones.aquarius.servicioretrofit.Controlador.PecesControlator;
import com.artecinnovaciones.aquarius.servicioretrofit.WebService.PecesWebService;
import com.artecinnovaciones.aquarius.servicioretrofit.modelresponse.PecesEnfermedadesResponse;
import com.artecinnovaciones.aquarius.utilidades.ViewUtil;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Geovany.Chin on 19/07/2016.
 */
public class EnfermedadesPecesService extends BaseService<PecesWebService> {

    public static final String TAG = PecesWebService.class.getSimpleName();

    public EnfermedadesPecesService(String baseUrl) {
        super(baseUrl, PecesWebService.class);

    }

    public PecesEnfermedadesResponse getlistPecesEnfermedades(final Context mContext) {
        mPecesEnfermedadesResponse = null;

        Call<PecesEnfermedadesResponse> call = getWebServiceClient().getListPecesEnfermedades();
        call.enqueue(new Callback<PecesEnfermedadesResponse>() {
            @Override
            public void onResponse(Call<PecesEnfermedadesResponse> call, Response<PecesEnfermedadesResponse> response) {
                mPecesEnfermedadesResponse = response.body();

                EnfermedadesControlator.getInstance(mContext).guardarpecesEnfermedadesbd(mPecesEnfermedadesResponse);
            }

            @Override
            public void onFailure(Call<PecesEnfermedadesResponse> call, Throwable t) {

            }
        });
        return mPecesEnfermedadesResponse;
    }

    public String getImage(String image,Context mcontext) {
        String registrationImageBd = null;
        try {

            Call<ResponseBody> call = getWebServiceClient().getImagePeces("/aquarius/uploads/" + image);
            Response<ResponseBody> response = call.execute();

            if (response != null) {
                new ViewUtil().makeFile(mcontext ,response.body(),image);
              //  return new ViewUtil().TEMP_DIRECTORY_PATH + "IMG_TEMP_" + image;
                return mcontext.getFilesDir().getPath() + "/" + image;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return registrationImageBd;
    }

    @Deprecated//metodo Asyncrono
    private PecesEnfermedadesResponse getResponse(Call<PecesEnfermedadesResponse> call) {
        mPecesEnfermedadesResponse = null;
        call.enqueue(new Callback<PecesEnfermedadesResponse>() {
            @Override
            public void onResponse(Call<PecesEnfermedadesResponse> call, Response<PecesEnfermedadesResponse> response) {
                if (response != null) {
                    mPecesEnfermedadesResponse = response.body();
                }
            }

            @Override
            public void onFailure(Call<PecesEnfermedadesResponse> call, Throwable t) {
                t.getMessage();

            }
        });

        return mPecesEnfermedadesResponse;
    }

    public PecesEnfermedadesResponse mPecesEnfermedadesResponse;
}
