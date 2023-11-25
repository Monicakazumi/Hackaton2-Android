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

public class BuscarUsuarios extends AsyncTask<String, Void, Boolean> {

    private String usuarioDigitado;
    private String senhaDigitada;
    private VerificacaoLoginListener listener;

    public BuscarUsuarios(String usuarioDigitado, String senhaDigitada, VerificacaoLoginListener listener) {
        this.usuarioDigitado = usuarioDigitado;
        this.senhaDigitada = senhaDigitada;
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            String link = strings[0];
            URL url = new URL(link);
            URLConnection connection = url.openConnection();
            InputStream stream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(stream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String dados = "";
            String linha;

            while ((linha = reader.readLine()) != null) {
                dados += linha;
            }

            JSONArray lista = new JSONArray(dados);

            for (int i = 0; i < lista.length(); i++) {
                JSONObject item = (JSONObject) lista.get(i);

                String usuario = item.getString("nome");
                String senha = item.getString("senha");

                if (usuario.equals(usuarioDigitado) && senha.equals(senhaDigitada)) {
                    return true; // Encontrou um usuário com as credenciais fornecidas
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false; // Não encontrou um usuário com as credenciais fornecidas
    }

    @Override
    protected void onPostExecute(Boolean loginAceito) {
        listener.onLoginVerificado(loginAceito);
    }

    public interface VerificacaoLoginListener {
        void onLoginVerificado(boolean loginAceito);
    }
}
