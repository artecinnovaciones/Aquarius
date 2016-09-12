package com.artecinnovaciones.aquarius.servicioretrofit.modelresponse;

import com.artecinnovaciones.aquarius.objetos.Peces;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Geovany.Chin on 02/09/2016.
 */
public class CompararBd {
    @SerializedName("idEnfermedades")
    public int idEnfermedades;
    @SerializedName("idPeces")
    public int idPeces;
    @SerializedName("success")
    public int success;
    @SerializedName("CompararBd")
    public List<CompararBd> mCompararBd;
    public int bdinterna;

    public int getIdEnfermedades() {
        return idEnfermedades;
    }

    public void setIdEnfermedades(int idEnfermedades) {
        this.idEnfermedades = idEnfermedades;
    }

    public int getIdPeces() {
        return idPeces;
    }

    public void setIdPeces(int idPeces) {
        this.idPeces = idPeces;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }


    public List<CompararBd> getmCompararBd() {
        return mCompararBd;
    }

    public void setmCompararBd(List<CompararBd> mCompararBd) {
        this.mCompararBd = mCompararBd;
    }

    public int getBdinterna() {
        return bdinterna;
    }

    public void setBdinterna(int bdinterna) {
        this.bdinterna = bdinterna;
    }
}
