package com.example.krauser.restauranteandroid.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.krauser.restauranteandroid.R;
import com.example.krauser.restauranteandroid.adapter.itemPedidoListAdapter;
import com.example.krauser.restauranteandroid.infra.repositorio.ItemPedidoRepositorio;
import com.example.krauser.restauranteandroid.model.ItemPedido;

import java.util.ArrayList;
import java.util.List;

public class ItensPedido extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itens_pedido);
        setTitle("Adicionar itens ao pedido");


        List<ItemPedido> itens = new ArrayList<ItemPedido>();
        try{
            ItemPedidoRepositorio repositorio = new ItemPedidoRepositorio(this);
            itens = repositorio.obterTodos();
        }catch(Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.itemPedidoInternaRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new itemPedidoListAdapter(itens, this));


    }
}
