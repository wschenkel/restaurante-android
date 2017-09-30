package com.example.krauser.restauranteandroid.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.krauser.restauranteandroid.R;
import com.example.krauser.restauranteandroid.adapter.ItemListAdapter;
import com.example.krauser.restauranteandroid.infra.repositorio.ItemRepositorio;
import com.example.krauser.restauranteandroid.model.Item;
import com.example.krauser.restauranteandroid.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class ItensPedido extends BaseActivity {

    private Pedido pedido;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itens_pedido);
        setTitle("Adicionar itens ao pedido");

        pedido = (Pedido)getIntent().getSerializableExtra("pedido");

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
        final ItemListAdapter adapter = new ItemListAdapter(itens, this);
        adapter.setSelectable(true);
        recyclerView.setAdapter(adapter);

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
        menu.addSubMenu("Teste");
        return true;
    }
}
