package com.example.hachathon2.models;

import com.example.hachathon2.ConfigRotas;

public class Rota {

    public String nome;
    public String origem;
    public String destino;
    public String hora;
    public String preco;

    public String url;

    public int id() {
        if (url != null){
            String codigo = url.replace(ConfigRotas.linkServer,"");
            return Integer.parseInt(codigo);
        }
        return 0;
    }

}
