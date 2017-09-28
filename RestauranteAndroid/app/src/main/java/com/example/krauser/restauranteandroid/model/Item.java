package com.example.krauser.restauranteandroid.model;

import com.example.krauser.restauranteandroid.Constants;

public class Item {

    public int id;
    public String urlImagem;
    public String titulo;
    public String descricao;

    public static String getSqlCreateTable(){
        String sql = "CREATE TABLE " + Constants.ITEM_TABLE + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo TEXT, " +
                "descricao TEXT, " +
                "urlImagem TEXT)";
        return sql;
    }
}
