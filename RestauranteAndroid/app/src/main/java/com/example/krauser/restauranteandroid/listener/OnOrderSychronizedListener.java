package com.example.krauser.restauranteandroid.listener;

public interface OnOrderSychronizedListener {
    void orderSynchronized(int erro);
    void orderAdded(int erro, String msg);
}
