package com.example.krauser.restauranteandroid.activity;

import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import com.example.krauser.restauranteandroid.R;
import com.example.krauser.restauranteandroid.adapter.PedidoListAdapter;
import com.example.krauser.restauranteandroid.infra.repositorio.PedidoRepositorio;
import com.example.krauser.restauranteandroid.model.Pedido;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CardView cardView;
    private Button novoPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Pedidos");
        List<Pedido> pedidos = new ArrayList<>();
        try{
            PedidoRepositorio repositorio = new PedidoRepositorio(this);
            pedidos = repositorio.obterTodos();
        }catch(Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.itemPedidoRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new PedidoListAdapter(pedidos, this));

//        String urlImage = "https://institucional-statics-files.s3.amazonaws.com/Prime-Rib-Pasta-cmyk-massas.jpg";
//        ImageView img = (ImageView)findViewById(R.id.imgItem);
//        Picasso.with(this).load(urlImage).into(img);
//
//        cardView = (CardView)findViewById(R.id.cardView);
//        cardView.getBackground().setAlpha(128);
//
//        Button novoPedido = (Button) findViewById(R.id.novoPedidoButton);
//        novoPedido.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent it = new Intent(MainActivity.this, NovoPedido.class);
//                startActivity(it);
//            }
//        });

    }
}