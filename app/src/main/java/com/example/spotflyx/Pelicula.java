package com.example.spotflyx;

public class Pelicula {

    int IdPelicula;
    String Titulo;
    String Genero;
    String Descripcion;
    String Director;
    String Portada;
    String Link;

    public Pelicula(int idPelicula, String titulo, String genero, String des, String director, String portada, String link) {

        IdPelicula = idPelicula;
        Titulo = titulo;
        Genero = genero;
        Descripcion = des;
        Director = director;
        Portada = portada;
        Link = link;
    }

    public Pelicula() {

    }

    public int getIdPelicula() {
        return IdPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        IdPelicula = idPelicula;
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

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "IdPelicula=" + IdPelicula +
                ", Titulo='" + Titulo + '\'' +
                ", Genero='" + Genero + '\'' +
                ", Descripcion='" + Descripcion + '\'' +
                ", Director='" + Director + '\'' +
                ", Portada='" + Portada + '\'' +
                ", Link='" + Link + '\'' +
                '}';
    }

    public String getPortada() {
        return Portada;
    }

    public void setPortada(String portada) {
        Portada = portada;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

}
