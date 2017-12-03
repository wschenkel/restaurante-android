package com.example.krauser.restauranteandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.krauser.restauranteandroid.R;
import com.example.krauser.restauranteandroid.adapter.PedidoListAdapter;
import com.example.krauser.restauranteandroid.infra.repositorio.PedidoRepositorio;
import com.example.krauser.restauranteandroid.infra.repositorio.TokenRepository;
import com.example.krauser.restauranteandroid.listener.OnTokenListener;
import com.example.krauser.restauranteandroid.model.Pedido;
import com.example.krauser.restauranteandroid.service.ItemService;
import com.example.krauser.restauranteandroid.service.TokenService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends BaseActivity implements OnTokenListener{

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
        updateToken();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarPedidos();
    }

    private void updateToken(){
        TokenService tokenService = new TokenService(this);
        tokenService.setListener(this);
        showLoader("Atualizando token");
        tokenService.update();
    }

    private void initializeComponents(){
        setTitle("Pedidos");
        Button novoPedido = (Button) findViewById(R.id.buttonNovoPedido);
        novoPedido.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goPedido(v);
            }
        });
        recyclerView = (RecyclerView)findViewById(R.id.pedidoRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void carregarPedidos(){
        List<Pedido> pedidos = new ArrayList<>();
        try{
            PedidoRepositorio repositorio = new PedidoRepositorio(this);
            pedidos = repositorio.obterTodos();
            Collections.sort(pedidos);
        }catch(Exception ex){
            String msg = ex.getMessage();
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }
        recyclerView.setAdapter(new PedidoListAdapter(pedidos, this));
    }

    public void goPedido(View view){
        Intent it = new Intent(this, NovoPedido.class);
        startActivity(it);
    }

    @Override
    public void tokenUpdated(String msg) {
        hideLoader();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        syncData();
    }
}