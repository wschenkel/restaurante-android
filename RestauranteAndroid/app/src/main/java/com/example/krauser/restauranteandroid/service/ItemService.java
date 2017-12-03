package com.example.krauser.restauranteandroid.service;

import android.content.Context;
import android.database.SQLException;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.krauser.restauranteandroid.infra.repositorio.ItemRepositorio;
import com.example.krauser.restauranteandroid.listener.OnItemSynchronizedListener;
import com.example.krauser.restauranteandroid.model.Item;
import com.example.krauser.restauranteandroid.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ItemService {

    private Context context;
    private OnItemSynchronizedListener listener;
    private ItemRepositorio repository;

    public ItemService(Context context){
        this.context = context;
        repository = new ItemRepositorio(context);
    }

    public void syncData() throws SQLException {

        RequestQueue queue = Volley.newRequestQueue(context);
        String urlRequest = Constants.URL_API + "/api/items";
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                urlRequest,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            List<Item> itens = new ArrayList<>();
                            JSONArray array = response.getJSONArray("items");
                            for (int x = 0; x < array.length(); x++) {
                                JSONObject item = array.getJSONObject(x);
                                itens.add(new Item(item));
                            }
                            afterResponse(itens);
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

    public void setListener(OnItemSynchronizedListener listener){
        this.listener = listener;
    }

    private void error(String msg){
        String err = msg;
    }

    private void afterResponse(List<Item> itens){
        for(Item i : itens)
            repository.inserir(i);
        listener.itemSynchronized(0);
    }
}
