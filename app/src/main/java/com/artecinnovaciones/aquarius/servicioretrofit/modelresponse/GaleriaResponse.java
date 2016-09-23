package com.artecinnovaciones.aquarius.servicioretrofit.modelresponse;

import com.artecinnovaciones.aquarius.objetos.Galeria;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by LAP-NIDIA on 22/09/2016.
 */
public class GaleriaResponse {

    @SerializedName("Galeria")
    public List<Galeria> mListPeces;

    public List<Galeria> getmListPeces() { return mListPeces; }

    public void setmListPeces(List<Galeria> mListPeces) {
        this.mListPeces = mListPeces;
    }
}
