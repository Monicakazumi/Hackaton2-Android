package com.example.hachathon2.datasource;

import android.os.AsyncTask;

import com.example.hachathon2.models.Usuario;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class BuscarUsuarios extends AsyncTask<String, Void, ArrayList<Usuario>> {

    @Override
    protected ArrayList<Usuario> doInBackground(String... strings) {
        ArrayList<Usuario> listaDados = new ArrayList<Usuario>();

        try {
            String link = strings[0];
            URL url = new URL(link);
            URLConnection connection = url.openConnection();
            InputStream stream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(stream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String dados = "";
            String linha;

            while ((linha = reader.readLine()) != null){
                dados += linha;
            }

            JSONArray lista = new JSONArray(dados);

            for (int i = 0; i < lista.length(); i++) {
                JSONObject item = (JSONObject)lista.get(i);

                Usuario usuario = new Usuario();
                usuario.usuario = item.getString("usuario");
                usuario.senha = item.getString("senha");

                listaDados.add(usuario);
            }
        }
        catch (Exception ex) {

        }
        return listaDados;
    }
}
