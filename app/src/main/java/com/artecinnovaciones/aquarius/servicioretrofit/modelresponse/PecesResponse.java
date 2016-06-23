package com.artecinnovaciones.aquarius.servicioretrofit.modelresponse;

import java.util.List;

/**
 * Created by Geovany.Chin on 23/06/2016.
 */
public class PecesResponse {
    public List<PecesResponse> mListPeces;
    public String NombreCientifico;
    public String NombreComun;
    public String Informacion;
    public String Cuidados;
    public String Alimentacion;
    public String MasBuscado;
    public String Imagen;

    public String getNombreCientifico() {
        return NombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        NombreCientifico = nombreCientifico;
    }

    public String getNombreComun() {
        return NombreComun;
    }

    public void setNombreComun(String nombreComun) {
        NombreComun = nombreComun;
    }

    public String getInformacion() {
        return Informacion;
    }

    public void setInformacion(String informacion) {
        Informacion = informacion;
    }

    public String getCuidados() {
        return Cuidados;
    }

    public void setCuidados(String cuidados) {
        Cuidados = cuidados;
    }

    public String getAlimentacion() {
        return Alimentacion;
    }

    public void setAlimentacion(String alimentacion) {
        Alimentacion = alimentacion;
    }

    public String getMasBuscado() {
        return MasBuscado;
    }

    public void setMasBuscado(String masBuscado) {
        MasBuscado = masBuscado;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public List<PecesResponse> getmListPeces() {
        return mListPeces;
    }

    public void setmListPeces(List<PecesResponse> mListPeces) {
        this.mListPeces = mListPeces;
    }
}
