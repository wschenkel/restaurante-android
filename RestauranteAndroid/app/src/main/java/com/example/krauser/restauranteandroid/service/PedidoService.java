package com.example.krauser.restauranteandroid.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.krauser.restauranteandroid.infra.repositorio.PedidoRepositorio;
import com.example.krauser.restauranteandroid.listener.OnOrderSychronizedListener;
import com.example.krauser.restauranteandroid.model.Pedido;
import com.example.krauser.restauranteandroid.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoService {

    private OnOrderSychronizedListener listener;
    private Context context;
    private PedidoRepositorio repository;
    private Pedido currentOrder;

    public PedidoService(Context context){
        this.context = context;
        repository = new PedidoRepositorio(context);
    }

    public void syncData() {

        RequestQueue queue = Volley.newRequestQueue(context);
        String urlRequest = Constants.URL_API + "/api/orders";
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                urlRequest,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            List<Pedido> orders = new ArrayList<>();
                            JSONArray array = response.getJSONArray("orders");
                            for (int x = 0; x < array.length(); x++) {
                                JSONObject order = array.getJSONObject(x);
                                orders.add(new Pedido(order));
                            }
                            afterResponse(orders);
                        }catch(JSONException e){
                            error(e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error(error.getMessage());
                    }
                }
        );
        queue.add(request);
    }

    public void sendOrder(Pedido pedido) throws JSONException {
        currentOrder = pedido;
        RequestQueue queue = Volley.newRequestQueue(context);
        String urlRequest = Constants.URL_API + "/api/orders";
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                urlRequest,
                pedido.toJson(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            orderSent(1, response.getString("data"));
                        }catch(JSONException e){
                            orderSent(1, e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error(error.getMessage());
                        orderSent(1, error.getMessage());
                    }
                }
        );
        queue.add(request);
    }

    public void setListener(OnOrderSychronizedListener listener){
        this.listener = listener;
    }

    private void error(String msg){
        Log.d("PEDIDO_SERVICE", msg);
        listener.orderSynchronized(1);
    }

    private void afterResponse(List<Pedido> pedidos){
        try{
            for(Pedido p : pedidos)
                repository.inserir(p);
            listener.orderSynchronized(0);
        }catch(Exception e){
            listener.orderSynchronized(1);
        }
    }

    private void orderSent(int erro, String msg){
        try{
            repository.inserir(currentOrder);
        }catch(Exception e){
            msg = "Enviado para api, mas não foi atualizado local";
        }
        if(listener != null)
            listener.orderAdded(erro, msg);
    }
}
