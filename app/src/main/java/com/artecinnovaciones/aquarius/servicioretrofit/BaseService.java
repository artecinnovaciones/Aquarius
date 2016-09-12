package com.artecinnovaciones.aquarius.servicioretrofit;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by geovany.chin on 16/12/2015.
 */


public abstract class BaseService<T> {

    protected BaseService(String baseUrl, Class<T> webService) {
        // TODO Auto-generated constructor stub
        this.mBaseUrl = baseUrl;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        this.mWebServiceClient = retrofit.create(webService);
    }


    public final String getBaseUrl() {
        return mBaseUrl;
    }

    public final T getWebServiceClient() {
        T a = mWebServiceClient;
        return a;
    }

    private String mBaseUrl;
   // private RestAdapter mRestAdapter;
    private T mWebServiceClient;

}
