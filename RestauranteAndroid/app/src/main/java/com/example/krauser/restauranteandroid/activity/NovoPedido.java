package com.example.krauser.restauranteandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.example.krauser.restauranteandroid.R;
import com.example.krauser.restauranteandroid.model.Item;
import com.example.krauser.restauranteandroid.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class NovoPedido extends BaseActivity {

    private CardView mCardView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novo_pedido);
        setTitle("Novo Pedido");

        List<Pedido> pedidos = new ArrayList<Pedido>();

        Pedido pedido = new Pedido();
        pedido.nome = "Willian";
        pedido.total = 114.50;
        pedido.mesa = 18;

        pedidos.add(pedido);
        pedidos.add(pedido);
        pedidos.add(pedido);


        Button btnItensPedido = (Button) findViewById(R.id.btnItensPedido);
        btnItensPedido.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            Intent it = new Intent(NovoPedido.this, ItensPedido.class);
            startActivity(it);
            }
        });
    }
}