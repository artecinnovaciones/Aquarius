package com.artecinnovaciones.aquarius.servicioretrofit.modelresponse;

import com.artecinnovaciones.aquarius.objetos.Peces;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Geovany.Chin on 23/06/2016.
 */
public class PecesResponse {
    @SerializedName("Peces")
    public List<Peces> mListPeces;

    public List<Peces> getmListPeces() {
        return mListPeces;
    }

    public void setmListPeces(List<Peces> mListPeces) {
        this.mListPeces = mListPeces;
    }
}
