package com.example.krauser.restauranteandroid.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.krauser.restauranteandroid.R;
import com.example.krauser.restauranteandroid.adapter.ItemListAdapter;
import com.example.krauser.restauranteandroid.infra.repositorio.ItemRepositorio;
import com.example.krauser.restauranteandroid.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItensPedido extends BaseActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itens_pedido);
        setTitle("Adicionar itens ao pedido");


        List<Item> itens = new ArrayList<>();
        try{
            ItemRepositorio repositorio = new ItemRepositorio(this);
            itens = repositorio.obterTodos();
        }catch(Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.itemPedidoInternaRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new ItemListAdapter(itens, this));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_item, menu);
        menu.addSubMenu("Teste");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
