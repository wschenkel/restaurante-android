package com.example.krauser.restauranteandroid.model;

public class Item {

    public static final String TABLE_NAME = "item";

    public int id;
    public String urlImagem;
    public String titulo;
    public String descricao;

    public static String getSqlCreateTable(){
        String sql = "CREATE TABLE " + TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo TEXT, " +
                "descricao TEXT, " +
                "urlImagem TEXT)";
        return sql;
    }
}
