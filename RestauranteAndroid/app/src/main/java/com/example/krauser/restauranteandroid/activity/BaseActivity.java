package com.example.krauser.restauranteandroid.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.krauser.restauranteandroid.R;

public class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = this.getWindow().getDecorView().getRootView();
        rootView.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background));
    }
}
