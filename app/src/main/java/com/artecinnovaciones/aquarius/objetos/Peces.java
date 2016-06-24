package com.artecinnovaciones.aquarius.objetos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Geovany.Chin on 23/06/2016.
 */
public class Peces {
    @SerializedName("NombreCientifico")
    public String NombreCientifico;
    @SerializedName("NombreComun")
    public String NombreComun;
    @SerializedName("Informacion")
    public String Informacion;
    @SerializedName("Cuidados")
    public String Cuidados;
    @SerializedName("Alimentacion")
    public String Alimentacion;
    @SerializedName("MasBuscado")
    public String MasBuscado;
    @SerializedName("Imagen")
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
}
