package com.example.krauser.restauranteandroid.model;

import com.example.krauser.restauranteandroid.util.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Item implements Serializable {

    public int id;
    public String titulo;
    public String imagem;
    public String descricao;
    public String categoria;
    public double valor;

    public Item(){}

    public Item(JSONObject item) throws JSONException {
        id = item.getInt("id");
        titulo = item.getString("name");
        descricao = item.getString("description");
        categoria = item.getString("category");
        valor = item.getLong("value");
        imagem = item.getString("imagePath");
    }

    public static String getSqlCreateTable(){
        String sql = "CREATE TABLE " + Constants.ITEM_TABLE + " (" +
                "id INTEGER PRIMARY KEY, " +
                "titulo TEXT, " +
                "descricao TEXT, " +
                "imagem INTEGER, " +
                "categoria TEXT," +
                "valor FLOAT)";
        return sql;
    }
}
