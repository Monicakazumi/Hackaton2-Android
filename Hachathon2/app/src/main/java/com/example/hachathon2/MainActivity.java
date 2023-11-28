package com.example.hachathon2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hachathon2.NavFragments.SharedPreManager;
import com.example.hachathon2.datasource.BuscarUsuarios;
import com.example.hachathon2.models.Usuario;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

        // Verifica se há um login salvo
        if (isUserLoggedIn()) {
            // Se houver um login salvo, vá diretamente para a atividade desejada
            startActivity(new Intent(MainActivity.this, RotasActivity.class));
            finish(); // Finaliza a atividade atual para que o usuário não possa voltar pressionando o botão de volta
        }
    }

    private void realizarLogin() {
        String usuarioDigitado = edtUsuario.getText().toString();
        String senhaDigitada = edtSenha.getText().toString();

        // Substitua "sua_url_da_api" pela URL real da sua API
        String apiUrl = "http://172.16.11.62:3000/usuario";

        // Cria uma instância de BuscarUsuarios para verificar o login
        BuscarUsuarios buscarUsuarios = new BuscarUsuarios(usuarioDigitado, senhaDigitada, new BuscarUsuarios.VerificacaoLoginListener() {
            @Override
            public void onLoginVerificado(boolean loginAceito) {
                if (loginAceito) {
                    // Login bem-sucedido, salva o login e inicia a atividade desejada
                    salvarLogin();
                    startActivity(new Intent(MainActivity.this, RotasActivity.class));
                    finish(); // Finaliza a atividade atual
                } else {
                    // Login falhou, exibe uma mensagem de erro
                    Toast.makeText(MainActivity.this, "Credenciais inválidas", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                    alerta.setTitle("Usuário ou Senha Inválida");
                    alerta.setNeutralButton("OK", null);
                    alerta.show();
                }
            }
        });

        // Executa a tarefa de verificação de login em segundo plano
        buscarUsuarios.execute(apiUrl);
    }

    private void salvarLogin() {
        Usuario usuario = new Usuario(); // Certifique-se de obter as informações corretas do usuário aqui

        // Use SharedPreManager para salvar o usuário e manter o login
        SharedPreManager sharedPreManager = new SharedPreManager(MainActivity.this);
        sharedPreManager.SalvarUser(usuario);
    }


    private boolean isUserLoggedIn() {
        // Verifica se o usuário já está logado usando SharedPreManager
        SharedPreManager sharedPreManager = new SharedPreManager(MainActivity.this);
        return sharedPreManager.isUserLoggedIn();
    }

}