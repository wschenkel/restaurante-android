package com.example.krauser.restauranteandroid.model;

import com.example.krauser.restauranteandroid.Constants;

import java.util.List;

public class Pedido {

    public int id;
    public String nome;
    public int mesa;
    public double total;
    public String resumo;
    public String observacao;
    public String data;
    public List<Item> itens;

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
}
