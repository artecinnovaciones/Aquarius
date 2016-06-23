package com.artecinnovaciones.aquarius.servicioretrofit;

import com.artecinnovaciones.aquarius.servicioretrofit.WebService.PecesWebService;

/**
 * Created by Geovany.Chin on 23/06/2016.
 */
public class PecesService  extends BaseService<PecesWebService> {

    public static final String TAG = PecesWebService.class.getSimpleName();

    public PecesService(String baseUrl) {
        super(baseUrl, PecesWebService.class);

    }

    }
