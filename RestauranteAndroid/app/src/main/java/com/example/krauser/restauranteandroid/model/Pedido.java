package com.example.krauser.restauranteandroid.model;

import android.support.annotation.NonNull;

import com.example.krauser.restauranteandroid.util.Constants;
import com.example.krauser.restauranteandroid.util.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido implements Comparable<Pedido>, Serializable{

    public int id;
    public String nome;
    public int mesa;
    public String observacao;
    public String data;
    public List<Item> itens;
    public String situacao;
    private String resumo;


    public Pedido(){
        itens = new ArrayList<>();
    }

    public Pedido(JSONObject json) throws JSONException
    {
        id = json.getInt("id");
        data = json.getString("date");
        mesa = json.getInt("table");
        nome = json.getString("name");
        resumo = json.getString("resume");
        situacao = json.getString("status");
        itens = new ArrayList<>();
        JSONArray array = json.getJSONArray("items");
        for (int x = 0; x < array.length(); x++)
            itens.add(new Item(array.getJSONObject(x)));
    }

    public String getTotalMoney(){
        double valor = 0;
        for(Item i : itens)
            valor += i.valor;
        return String.format("R$ %.2f", valor);
    }

    public String getResumo() {
        if(resumo != null && !resumo.isEmpty())
            return resumo;

        String descricao = "";
        for(Item i : itens)
            descricao += String.format(i.titulo + " | ");
        return descricao.substring(0, descricao.length() - 3) + ".";
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public static String getSqlCreateTable(){
        String sql = "CREATE TABLE " + Constants.PEDIDO_TABLE + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, " +
                "mesa INTEGER, " +
                "total FLOAT, " +
                "resumo TEXT, " +
                "data TEXT, " +
                "observacao TEXT)";
        return sql;
    }

    @Override
    public int compareTo(@NonNull Pedido pedido) {
        Date selfDate = Helper.stringToDate(this.data);
        Date youDate = Helper.stringToDate(pedido.data);
        if(selfDate.compareTo(youDate) > 0)
            return - 1;
        else if(selfDate.compareTo(youDate) < 0)
            return + 1;
        else
            return 0;
    }
}
