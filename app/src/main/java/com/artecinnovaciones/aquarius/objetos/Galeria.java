package com.artecinnovaciones.aquarius.objetos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LAP-NIDIA on 22/09/2016.
 */
public class Galeria {

    @SerializedName("id")
    public int id;
    @SerializedName("Descripcion")
    public String Descripcion;
    @SerializedName("Img")
    public String Img;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getDescripcion() { return Descripcion; }

    public void setDescripcion(String descripcion) { Descripcion = descripcion; }

    public String getImg() { return Img; }

    public void setImg(String img) { Img = img; }
}
