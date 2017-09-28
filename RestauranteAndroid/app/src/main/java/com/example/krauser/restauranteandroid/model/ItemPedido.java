package com.example.krauser.restauranteandroid.model;

public class ItemPedido {
    public int id;
    public String urlImagem;
    public String titulo;
    public String descricao;

    public static String getSqlCreateTable(){
        String sql = "CREATE TABLE itemPedido (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo TEXT, " +
                "descricao TEXT, " +
                "urlImagem TEXT)";
        return sql;
    }
}
