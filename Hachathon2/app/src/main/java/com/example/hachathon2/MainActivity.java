package com.example.hachathon2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hachathon2.datasource.BuscarUsuarios;

public class MainActivity extends AppCompatActivity {

    EditText edtSenha, edtUsuario;
    Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsuario = findViewById(R.id.edtUsuario);
        edtSenha = findViewById(R.id.edtSenha);
        btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarLogin();
            }
        });
    }

    private void realizarLogin() {
        String usuarioDigitado = edtUsuario.getText().toString();
        String senhaDigitada = edtSenha.getText().toString();

        // Substitua "sua_url_da_api" pela URL real da sua API
        String apiUrl = "http://192.168.3.30:3000/usuario";

        // Cria uma instância de BuscarUsuarios para verificar o login
        BuscarUsuarios buscarUsuarios = new BuscarUsuarios(usuarioDigitado, senhaDigitada, new BuscarUsuarios.VerificacaoLoginListener() {
            @Override
            public void onLoginVerificado(boolean loginAceito) {
                if (loginAceito) {
                    // Login bem-sucedido, inicia a atividade desejada
                    startActivity(new Intent(MainActivity.this, RotasActivity.class));
                } else {
                    // Login falhou, exibe uma mensagem de erro
                    Toast.makeText(MainActivity.this, "Credenciais inválidas", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(this, texto, Toast.LENGTH_LONG).show();
                    AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                    alerta.setTitle("Usuário ou Senha Inválida!");;
                    alerta.setNeutralButton("OK", null);
                    alerta.show();
                }
            }
        });

        // Executa a tarefa de verificação de login em segundo plano
        buscarUsuarios.execute(apiUrl);
    }
}
