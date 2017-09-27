package com.example.krauser.restauranteandroid.model;

import java.util.Date;

public class Pedido {

    public int id;
    public String nome;
    public int mesa;
    public double total;
    public String resumo;
    public String observacao;
    public String data;

    public static String getCreateTableSql(){
        String sql = "CREATE TABLE Pedido (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, " +
                "mesa INTEGER, " +
                "total FLOAT, " +
                "resumo TEXT, " +
                "observacao TEXT)";
        return sql;
    }
}
