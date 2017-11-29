package com.example.krauser.restauranteandroid.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.krauser.restauranteandroid.R;
import com.example.krauser.restauranteandroid.adapter.FilterableList;
import com.example.krauser.restauranteandroid.adapter.ItemListAdapter;
import com.example.krauser.restauranteandroid.infra.repositorio.ItemRepositorio;
import com.example.krauser.restauranteandroid.model.Item;
import com.example.krauser.restauranteandroid.model.Pedido;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class ItensPedido extends BaseActivity {

    private Pedido pedido;
    private ItemListAdapter adapter;

    private String currentFilter;
    private Predicate<Item> filterOk;

    String URL = "https://restaurante-api-react.herokuapp.com/api/items";

    private boolean filterOk(Item item){
        if(currentFilter == null || currentFilter.isEmpty())
            return true;
        return item.categoria.toLowerCase().equals(currentFilter.toLowerCase());
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itens_pedido);
        setTitle("Adicionar itens ao pedido");

        pedido = (Pedido)getIntent().getSerializableExtra("pedido");

        List<Item> itens = new ArrayList<>();
        RequestQueue fila = Volley.newRequestQueue(this);

        //try{
            //ItemRepositorio repositorio = new ItemRepositorio(this);
            //itens = repositorio.obterTodos();

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("items");

                            for (int x = 0; x < array.length(); x++) {
                                JSONObject item = array.getJSONObject(x);
                                Item i = new Item();
                                i.id = item.getInt("id");
                                i.titulo = item.getString("name");
                                i.descricao = item.getString("description");
                                i.categoria = item.getString("category");
                                i.valor = item.getLong("value");
                                i.resource = 0;
                                itens.add(i);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        doiT(itens);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                    }
                }
        );

        fila.add(request);


        Button btnSelecionar = (Button) findViewById(R.id.btnEscolher);

        btnSelecionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                List<Item> selecionados = adapter.getSelectedItens();
                pedido.itens.addAll(selecionados);
                resultIntent.putExtra("pedido", pedido);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(!item.toString().isEmpty())
            adapter.setFilter(item.toString());

        return super.onOptionsItemSelected(item);
    }

    public void doiT(List<Item> itens) {
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.itemPedidoInternaRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FilterableList list = new FilterableList<>(itens);

        adapter = new ItemListAdapter(list, this);

        recyclerView.setAdapter(adapter);
        adapter.setSelectable(true);
    }
}
