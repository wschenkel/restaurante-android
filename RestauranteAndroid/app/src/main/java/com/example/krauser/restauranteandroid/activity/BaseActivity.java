package com.example.krauser.restauranteandroid.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.krauser.restauranteandroid.R;
import com.example.krauser.restauranteandroid.infra.repositorio.ItemRepositorio;
import com.example.krauser.restauranteandroid.infra.repositorio.PedidoRepositorio;
import com.example.krauser.restauranteandroid.listener.OnItemSynchronizedListener;
import com.example.krauser.restauranteandroid.listener.OnOrderSychronizedListener;
import com.example.krauser.restauranteandroid.service.ItemService;
import com.example.krauser.restauranteandroid.service.PedidoService;


public class BaseActivity extends AppCompatActivity implements OnOrderSychronizedListener, OnItemSynchronizedListener{

    private ProgressDialog progress;
    protected ItemRepositorio itemRepository;
    protected PedidoRepositorio pedidoRepository;
    protected ItemService itemService;
    protected PedidoService pedidoService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        itemRepository = new ItemRepositorio(this);
        pedidoRepository = new PedidoRepositorio(this);
        pedidoService = new PedidoService(this);
        itemService = new ItemService(this);
        itemService.setListener(this);
        pedidoService.setListener(this);
        View rootView = this.getWindow().getDecorView().getRootView();
        rootView.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background));
    }

    protected void showLoader(String message){
        progress.setMessage(message);
        progress.show();
    }

    protected void hideLoader(){
        progress.hide();
    }

    protected void syncData(){
        showLoader("Sincronizando os dados...");
        itemRepository.removerTodos();
        pedidoRepository.removerTodos();
        itemService.syncData();
    }

    @Override
    public void itemSynchronized(int erro) {
        if(erro == 0){
            pedidoService.syncData();
        }else{
            hideLoader();
            Toast.makeText(this, "Não foi possível sincronizar os itens", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void orderSynchronized(int erro) {
        String msg = erro == 0 ? "Os dados foram sincronizados." : "Não foi possível sincronizar os itens";
        hideLoader();
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
