package com.example.spotflyx;

public class Usuario {
    String usuario;
    String contraseña;


    public Usuario(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }


    @Override
    public String toString() {
        return "  { \"usuario\": \" " + usuario + " \", \"contraseña\":\" " + contraseña + "\" }";
    }
}
