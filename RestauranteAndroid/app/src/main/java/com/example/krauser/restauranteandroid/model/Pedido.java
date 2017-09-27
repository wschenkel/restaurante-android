package com.example.krauser.restauranteandroid.model;

public class Pedido {

    public int id;
    public String nome;
    public int mesa;
    public double total;
    public String resumo;
    public String observacao;
    public String data;

    public static String getSqlCreateTable(){
        String sql = "CREATE TABLE pedido (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, " +
                "mesa INTEGER, " +
                "total FLOAT, " +
                "resumo TEXT, " +
                "data TEXT, " +
                "observacao TEXT)";
        return sql;
    }
}
