package com.artecinnovaciones.aquarius.servicioretrofit.modelresponse;

import com.artecinnovaciones.aquarius.objetos.Enfermedades;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Geovany.Chin on 19/07/2016.
 */
public class PecesEnfermedadesResponse {

    @SerializedName("Enfermedades")
    public List<Enfermedades> mListPeces;

    public List<Enfermedades> getmListPeces() {
        return mListPeces;
    }

    public void setmListPeces(List<Enfermedades> mListPeces) {
        this.mListPeces = mListPeces;
    }
}
