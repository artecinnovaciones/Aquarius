package com.artecinnovaciones.aquarius.objetos;

/**
 * Created by LAP-NIDIA on 16/06/2016.
 */
public class DetallesItem {
    private String titulo,descripcion;

    public DetallesItem(String titulo,String descripcion){
        this.titulo=titulo;
        this.descripcion=descripcion;
    }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion(){ return descripcion; }

    public void setDescripcion(String descripcion){ this.descripcion = descripcion; }
}
