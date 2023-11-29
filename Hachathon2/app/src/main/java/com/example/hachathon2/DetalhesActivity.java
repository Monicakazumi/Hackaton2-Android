package com.example.hachathon2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DetalhesActivity extends AppCompatActivity {

    TextView txtNome, txtOrigem, txtDestino, txtHora, txtPreco;
    Button btnComprar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        txtNome = findViewById(R.id.txtNome);
        txtOrigem = findViewById(R.id.txtOrigem);
        txtDestino = findViewById(R.id.txtDestino);
        txtHora = findViewById(R.id.txtHora);
        txtPreco = findViewById(R.id.txtPreco);
        btnComprar = findViewById(R.id.btnComprar);

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetalhesActivity.this, "Compra Realizada", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alerta = new AlertDialog.Builder(DetalhesActivity.this);
                alerta.setTitle("Obrigado por usar nosso app");
                alerta.setNeutralButton("OK", null);
                alerta.show();
                startActivity(new Intent(DetalhesActivity.this, RotasActivity.class));

            }
        });

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
