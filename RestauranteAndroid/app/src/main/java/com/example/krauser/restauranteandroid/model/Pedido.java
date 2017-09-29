package com.example.krauser.restauranteandroid.model;

import com.example.krauser.restauranteandroid.Constants;

import java.util.ArrayList;
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

    public static List<Pedido> getPedidosIniciais(){
        List<Pedido> pedidos = new ArrayList<>();
        Pedido pedido = new Pedido();
        pedido.nome = "Fulaninha";
        pedido.mesa = 7;
        pedido.total = 567.98;
        pedido.data = "09/07/17 - 13:30";
        pedido.resumo = "Champagne, CINNAMON OBLIVION, BLOOMIN’ ONION ...";

        pedidos.add(pedido);

        pedido = new Pedido();
        pedido.nome = "Ciclaninho";
        pedido.mesa = 18;
        pedido.total = 178.31;
        pedido.data = "30/08/17 - 09:45";
        pedido.resumo = "Absolut, Pave de limão, X salada ...";

        pedidos.add(pedido);

        return pedidos;
    }
}
