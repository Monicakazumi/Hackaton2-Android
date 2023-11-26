package com.example.hachathon2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalhesActivity extends AppCompatActivity {

    TextView txtNome, txtOrigem, txtDestino, txtHora, txtPreco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        txtNome = findViewById(R.id.txtNome);
        txtOrigem = findViewById(R.id.txtOrigem);
        txtDestino = findViewById(R.id.txtDestino);
        txtHora = findViewById(R.id.txtHora);
        txtPreco = findViewById(R.id.txtPreco);

        Intent caminhoRecebido = getIntent();

        if (caminhoRecebido != null) {
            Bundle parametros = caminhoRecebido.getExtras();

            if (parametros != null) {
                txtNome.setText(parametros.getString("nome"));
                txtOrigem.setText(parametros.getString("rotaInicial"));
                txtDestino.setText(parametros.getString("rotaFinal"));
                txtHora.setText(parametros.getString("horario"));
                txtPreco.setText(parametros.getString("preco"));
            }
        }
    }
}
