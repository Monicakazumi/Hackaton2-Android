package com.example.hachathon2.models;

import android.content.ContentValues;

import java.util.HashMap;

public class Usuario {

    public int id;
    public String usuario;
    public String senha;

    public Usuario() {

    }


    public int getId() {
        return id;
    }

    public Usuario(int id, String usuario, String senha) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
