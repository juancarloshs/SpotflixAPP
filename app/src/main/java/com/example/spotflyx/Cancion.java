package com.example.spotflyx;

import java.io.Serializable;

public class Cancion implements Serializable {
    String NombreCancion;

    String Link;

    public Cancion(String titulo, String link) {

        NombreCancion = titulo;
        Link = link;
    }

    public Cancion() {

    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getNombreCancion() {
        return NombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        NombreCancion = nombreCancion;
    }

}