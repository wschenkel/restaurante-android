package com.example.krauser.restauranteandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.example.krauser.restauranteandroid.R;
import com.example.krauser.restauranteandroid.adapter.PedidoListAdapter;
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

        List<Pedido> itens = new ArrayList<>();

        Pedido pedido = new Pedido();
        pedido.resumo = "Vinho, Massa carbonara, Costelinha com molho barbecue ...";
        pedido.mesa = 18;

        Format formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
        Date date = new Date();

        pedido.data = formatter.format(date);

        itens.add(pedido);
        itens.add(pedido);
        itens.add(pedido);
        itens.add(pedido);
        itens.add(pedido);
        itens.add(pedido);
        itens.add(pedido);
        itens.add(pedido);
        itens.add(pedido);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.itemPedidoRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new PedidoListAdapter(itens, this));



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