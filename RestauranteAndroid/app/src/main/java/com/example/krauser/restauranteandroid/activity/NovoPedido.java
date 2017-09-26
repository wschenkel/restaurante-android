package com.example.krauser.restauranteandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.example.krauser.restauranteandroid.R;

/**
 * Created by wschenkel on 24/09/17.
 */

public class NovoPedido extends AppCompatActivity {

    private CardView mCardView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novo_pedido);
        setTitle("Novo Pedido");


        Button novoPedido = (Button) findViewById(R.id.itensPedido);
        novoPedido.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(NovoPedido.this, ItensPedido.class);
                startActivity(it);
            }
        });
    }
}