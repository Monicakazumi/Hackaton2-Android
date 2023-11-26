package com.example.hachathon2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.hachathon2.datasource.BuscarRotas;
import com.example.hachathon2.models.Rota;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RotasActivity extends ListActivity {

    private ArrayList<Rota> listaDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotas);

        try {
            listaDados = new BuscarRotas().execute(ConfigRotas.link).get();

            ListAdapter adapter = new SimpleAdapter(this,
                    dadosToMap(listaDados),
                    R.layout.listview_modelo,
                    new String[] { "nome" },
                    new int[] { R.id.txtRota }
            );

            setListAdapter(adapter);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private List<HashMap<String,String>> dadosToMap(ArrayList<Rota> listaDados) {
        List<HashMap<String,String>> lista = new ArrayList<>();

        for (int i = 0; i< listaDados.size(); i++) {
            HashMap<String,String> item = new HashMap<>();
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
}
