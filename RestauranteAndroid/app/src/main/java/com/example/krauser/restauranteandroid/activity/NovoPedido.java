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
import android.widget.TextView;
import android.widget.Toast;

import com.example.krauser.restauranteandroid.R;
import com.example.krauser.restauranteandroid.adapter.FilterableList;
import com.example.krauser.restauranteandroid.adapter.ItemListAdapter;
import com.example.krauser.restauranteandroid.infra.repositorio.ItemRepositorio;
import com.example.krauser.restauranteandroid.infra.repositorio.PedidoRepositorio;
import com.example.krauser.restauranteandroid.model.Item;
import com.example.krauser.restauranteandroid.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class NovoPedido extends BaseActivity {

    EditText txtMesa;
    EditText txtNome;
    Button btnItens;
    TextView txtTotal;
    Pedido pedido;
    boolean isView = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novo_pedido);
        initializeComponents();
    }

    private void initializeComponents() {

        setTitle("Novo Pedido");

        txtMesa = (EditText)findViewById(R.id.txtNumeroMesa);
        txtNome = (EditText)findViewById(R.id.txtNome);
        txtTotal = (TextView) findViewById(R.id.ttValorTotal);

        btnItens= (Button) findViewById(R.id.btnItensPedido);

        isView = (pedido = (Pedido)getIntent().getSerializableExtra("pedido")) != null;

        if(!isView)
            pedido = new Pedido();
        else {
            disabilitarCampos();
            preencherDadosPedido();
        }

        btnItens.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            Intent intent = new Intent(NovoPedido.this, ItensPedido.class);
            intent.putExtra("pedido", pedido);
            startActivityForResult(intent, 1);
            }
        });
    }

    private void disabilitarCampos(){
        txtNome.setEnabled(false);
        txtMesa.setEnabled(false);
        btnItens.setVisibility(View.INVISIBLE);
        findViewById(R.id.btnSalvar).setVisibility(View.INVISIBLE);
    }

    private void preencherDadosPedido(){
        if(this.pedido != null){
            if(pedido.mesa > 0)
                txtMesa.setText(String.valueOf(pedido.mesa));
            txtTotal.setText(pedido.getTotalMoney());

            if (pedido.nome == null) {
                txtNome.setText(String.valueOf(txtNome.getText()));
            } else {
                txtNome.setText(String.valueOf(pedido.nome));
            }

            RecyclerView itemRecycler = (RecyclerView)findViewById(R.id.itemPedidoRecyclerView);

            itemRecycler.setHasFixedSize(true);
            itemRecycler.setLayoutManager(new LinearLayoutManager(this));

            FilterableList<Item> itens = new FilterableList<>(pedido.itens);

            itemRecycler.setAdapter(new ItemListAdapter(itens, this));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data != null && data.hasExtra("pedido")){
            isView = false;
            this.pedido = (Pedido)data.getSerializableExtra("pedido");
            preencherDadosPedido();
        }
    }

    public void salvar(View view){
        try{
            if(txtMesa.getText().toString().isEmpty())
                throw new Exception("Mesa inválida.");
            if(pedido.itens.size() == 0)
                throw new Exception("Insira itens no pedido");
            pedido.nome = String.valueOf(txtNome.getText());
            pedido.mesa = Integer.valueOf(txtMesa.getText().toString());
            new PedidoRepositorio(this).inserir(pedido);
            finish();
        }catch(Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}