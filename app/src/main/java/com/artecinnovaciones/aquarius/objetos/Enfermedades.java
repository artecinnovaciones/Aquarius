package com.artecinnovaciones.aquarius.objetos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Geovany.Chin on 19/07/2016.
 */
public class Enfermedades {

    @SerializedName("id")
    public int id;
    @SerializedName("Nombre")
    public String Nombre;
    @SerializedName("Sintomas")
    public String Sintomas;
    @SerializedName("Causas")
    public String Causas;
    @SerializedName("Tratamiento")
    public String Tratamiento;
    @SerializedName("img")
    public String img;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getSintomas() {
        return Sintomas;
    }

    public void setSintomas(String sintomas) {
        Sintomas = sintomas;
    }

    public String getCausas() {
        return Causas;
    }

    public void setCausas(String causas) {
        Causas = causas;
    }

    public String getTratamiento() {
        return Tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        Tratamiento = tratamiento;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
