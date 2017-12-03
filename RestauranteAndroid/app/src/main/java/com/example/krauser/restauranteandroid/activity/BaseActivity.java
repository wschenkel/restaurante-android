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


public class BaseActivity extends AppCompatActivity implements OnOrderSychronizedListener, OnItemSynchronizedListener{

    private ProgressDialog progress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
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
        showLoader("Os dados estão sendo atualizados");
        new ItemRepositorio(this).removerTodos();
        new PedidoRepositorio(this).removerTodos();
        ItemService itemService = new ItemService(this);
        itemService.setListener(this);
        itemService.syncData();
    }

    @Override
    public void itemSynchronized(int erro) {
        hideLoader();
        String msg = erro == 0 ? "Itens foram sincronizados" : "Não foi possível sincronizar os itens";
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void OrderSynchronized(int erro) {

    }
}
