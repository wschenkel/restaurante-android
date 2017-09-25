package com.example.krauser.restauranteandroid.infra.model;

public class Item {
    public static String getCreateTableSql(){
        String sql = "CREATE TABLE item (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, descricao TEXT, url_imagem)";
        return sql;
    }
}
