package com.example.krauser.restauranteandroid.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.krauser.restauranteandroid.R;
import com.example.krauser.restauranteandroid.adapter.FilterableList;
import com.example.krauser.restauranteandroid.adapter.ItemListAdapter;
import com.example.krauser.restauranteandroid.infra.repositorio.ItemRepositorio;
import com.example.krauser.restauranteandroid.model.Item;
import com.example.krauser.restauranteandroid.model.Pedido;

import java.util.List;
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


        ItemRepositorio repositorio = new ItemRepositorio(this);
        updateItems(repositorio.obterTodos());

        Button btnSelecionar = findViewById(R.id.btnEscolher);

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

    public void updateItems(List<Item> itens) {
        RecyclerView recyclerView = findViewById(R.id.itemPedidoInternaRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FilterableList list = new FilterableList<>(itens);

        adapter = new ItemListAdapter(list, this);

        recyclerView.setAdapter(adapter);
        adapter.setSelectable(true);
    }
}
