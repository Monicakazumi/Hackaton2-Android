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

            ListAdapter adapter = new SimpleAdapter(this, //this - onde a classe que tem o objeto listView no XML
                    dadosToMap(listaDados), //lista com os dados em formato HashMap
                    R.layout.listview_modelo, //layout de modelo para cada item da lista
                    new String[] { "nome" },  //campo dos dados que sera carregado na listaDados
                    new int[] { R.id.txtRota } //em que item da lista carregara os dados do listview
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
            item.put("id", String.valueOf(listaDados.get(i).id));
            item.put("nome", listaDados.get(i).nome);

            lista.add(item);
        }

        return lista;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //positon -> tem o indice do item que foi clicado na ListView
        Rota rota = listaDados.get(position);

        //criando o caminho para a tela de Detalhes
        Intent tela = new Intent(RotasActivity.this, DetalhesActivity.class);

        //criando objeto para enviar os dados para a detalhes
        Bundle parametros = new Bundle();
        parametros.putString("nome", rota.nome);

        //adicionando os paramentros no caminho da tela - put para adiciona
        tela.putExtras(parametros);

        //abrindo a tela de detralhes
        startActivity(tela);
    }
}
