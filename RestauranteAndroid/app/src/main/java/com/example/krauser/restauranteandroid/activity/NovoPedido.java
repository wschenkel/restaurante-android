package com.example.krauser.restauranteandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.krauser.restauranteandroid.R;
import com.example.krauser.restauranteandroid.adapter.ItemListAdapter;
import com.example.krauser.restauranteandroid.infra.repositorio.ItemRepositorio;
import com.example.krauser.restauranteandroid.model.Item;
import com.example.krauser.restauranteandroid.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class NovoPedido extends BaseActivity {

    EditText txtMesa;
    EditText txtNome;
    Button btnItens;
    List<Item> itens;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novo_pedido);
        initializeComponents();
    }

    private void initializeComponents(){
        setTitle("Novo Pedido");

        txtMesa = (EditText)findViewById(R.id.txtNumeroMesa);
        txtNome = (EditText)findViewById(R.id.txtNome);

        btnItens= (Button) findViewById(R.id.btnItensPedido);

        Pedido pedido = (Pedido)getIntent().getSerializableExtra("pedido");
        if(pedido != null){
            txtMesa.setText(String.valueOf(pedido.mesa));
            txtNome.setText(pedido.nome);
            disabilitarCampos();
            itens = pedido.itens;
        }else{
            itens = new ArrayList<>();
        }

        btnItens.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(NovoPedido.this, ItensPedido.class);
                startActivity(it);
            }
        });

        RecyclerView itemRecycler = (RecyclerView)findViewById(R.id.itemPedidoRecyclerView);

        itemRecycler.setHasFixedSize(true);
        itemRecycler.setLayoutManager(new LinearLayoutManager(this));

        itemRecycler.setAdapter(new ItemListAdapter(itens, this));
    }

    private void disabilitarCampos(){
        txtNome.setEnabled(false);
        txtMesa.setEnabled(false);
        btnItens.setVisibility(View.INVISIBLE);
    }
}