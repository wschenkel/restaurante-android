package com.example.krauser.restauranteandroid.model;

import android.support.annotation.NonNull;

import com.example.krauser.restauranteandroid.util.Constants;
import com.example.krauser.restauranteandroid.util.Helper;

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
    private String resumo;

    public Pedido(){
        itens = new ArrayList<>();
    }

    public double getTotal(){
        double valor = 0;
        for(Item i : itens)
            valor += i.valor;
        return valor;
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

    public static List<Pedido> getPedidosIniciais(){
        List<Pedido> pedidos = new ArrayList<>();
        Pedido pedido = new Pedido();
        pedido.nome = "Fulaninha";
        pedido.mesa = 7;
        pedido.data = "09/07/17 - 13:30";
        pedido.setResumo("Champagne, CINNAMON OBLIVION, BLOOMIN’ ONION ...");

        pedidos.add(pedido);

        pedido = new Pedido();
        pedido.nome = "Ciclaninho";
        pedido.mesa = 18;
        pedido.data = "30/08/17 - 09:45";
        pedido.setResumo("Absolut, Pave de limão, X salada ...");

        pedidos.add(pedido);

        return pedidos;
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
