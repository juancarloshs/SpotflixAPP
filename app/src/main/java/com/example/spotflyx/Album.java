package com.example.spotflyx;

import java.util.List;

public class Album {

    int IdAlbum;
    String Titulo;
    String Genero;
    String Artista;
    String Portada;

    List<Cancion> listaCanciones;

    public Album(int idAlbum, String titulo, String genero, String artista, String portada, List<Cancion> listaCanciones) {
        super();
        IdAlbum = idAlbum;
        Titulo = titulo;
        Genero = genero;
        Artista = artista;
        this.listaCanciones = listaCanciones;
        this.Portada = portada;
    }

    public String getPortada() {
        return Portada;
    }

    public void setPortada(String portada) {
        Portada = portada;
    }

    public Album() {

    }

    public int getIdAlbum() {
        return IdAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        IdAlbum = idAlbum;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }


    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public String getArtista() {
        return Artista;
    }

    public void setArtista(String artista) {
        Artista = artista;
    }

    public List<Cancion> getListaCanciones() {
        return listaCanciones;
    }

    public void setListaCanciones(List<Cancion> listaCanciones) {
        this.listaCanciones = listaCanciones;
    }

    @Override
    public String toString() {
        return "Album{" +
                "IdAlbum=" + IdAlbum +
                ", Titulo='" + Titulo + '\'' +
                ", Genero='" + Genero + '\'' +
                ", Artista='" + Artista + '\'' +
                ", Portada='" + Portada + '\'' +
                ", listaCanciones=" + listaCanciones +
                '}';
    }
}