package com.example.krauser.restauranteandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.krauser.restauranteandroid.R;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Pedidos");
        String urlImage = "https://institucional-statics-files.s3.amazonaws.com/Prime-Rib-Pasta-cmyk-massas.jpg";
        ImageView img = (ImageView)findViewById(R.id.imgItem);
        Picasso.with(this).load(urlImage).into(img);
    }
}