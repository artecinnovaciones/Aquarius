package com.artecinnovaciones.aquarius.objetos;

/**
 * Created by LAP-NIDIA on 16/06/2016.
 */
public class DetallesItem {
    public String titulo,descripcion,rutaImg;

    public DetallesItem(String titulo,String descripcion){
        this.titulo=titulo;
        this.descripcion=descripcion;
        this.rutaImg="";
    }

    public DetallesItem(String titulo,String descripcion,String rutaImg){
        this.titulo=titulo;
        this.descripcion=descripcion;
        this.rutaImg=rutaImg;
    }
}
