package com.example.krauser.restauranteandroid.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.krauser.restauranteandroid.R;

/**
 * Created by wschenkel on 26/09/17.
 */

public class ItensPedido extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itens_pedido);
        setTitle("Adicionar itens ao pedido");
    }
}
