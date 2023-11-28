package com.example.hachathon2;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;

import com.example.hachathon2.NavFragments.SharedPreManager;
import com.example.hachathon2.datasource.BuscarRotas;
import com.example.hachathon2.models.Rota;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RotasActivity extends ListActivity {

    private ArrayList<Rota> listaDados;
    private SharedPreManager sharedPreManager; // Adiciona esta linha

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotas);

        sharedPreManager = new SharedPreManager(this); // Adiciona esta linha

        Button btnLogoff = findViewById(R.id.btnSair);
        btnLogoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Executa a lógica de logoff
                realizarLogoff();
            }
        });

        try {
            listaDados = new BuscarRotas().execute(ConfigRotas.link).get();

            List<HashMap<String, String>> lista = dadosToMap(listaDados);

            String[] from = new String[]{"nome"};
            int[] to = new int[]{R.id.txtRota};

            SimpleAdapter adapter = new SimpleAdapter(this, lista, R.layout.listview_modelo, from, to);

            setListAdapter(adapter);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<HashMap<String, String>> dadosToMap(ArrayList<Rota> listaDados) {
        List<HashMap<String, String>> lista = new ArrayList<>();

        for (int i = 0; i < listaDados.size(); i++) {
            HashMap<String, String> item = new HashMap<>();
            item.put("id", String.valueOf(listaDados.get(i).id()));
            item.put("nome", listaDados.get(i).nome);
            item.put("rotaInicial", listaDados.get(i).origem);
            item.put("rotaFinal", listaDados.get(i).destino);
            item.put("horario", listaDados.get(i).hora);
            item.put("preco", listaDados.get(i).preco);

            lista.add(item);
        }

        return lista;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Rota rota = listaDados.get(position);

        Intent tela = new Intent(RotasActivity.this, DetalhesActivity.class);

        Bundle parametros = new Bundle();
        parametros.putString("nome", rota.nome);
        parametros.putString("rotaInicial", rota.origem);
        parametros.putString("rotaFinal", rota.destino);
        parametros.putString("horario", rota.hora);
        parametros.putString("preco", rota.preco);

        tela.putExtras(parametros);

        startActivity(tela);
    }

    // Lógica de logoff
    private void realizarLogoff() {
        // Utiliza SharedPreManager para realizar o logoff
        sharedPreManager.logout();

        Log.d(TAG, "Logoff realizado com sucesso");

        // Navegação de volta para a tela de login (MainActivity)
        Intent intent = new Intent(RotasActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        // Finaliza a atividade atual
        finish();
    }
}
