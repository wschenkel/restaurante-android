package com.example.krauser.restauranteandroid.model;

import com.example.krauser.restauranteandroid.Constants;
import com.example.krauser.restauranteandroid.R;

import java.util.ArrayList;
import java.util.List;

public class Item {

    public int id;
    public String titulo;
    public String descricao;
    public int resource;
    public String categoria;
    public double valor;

    public static String getSqlCreateTable(){
        String sql = "CREATE TABLE " + Constants.ITEM_TABLE + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo TEXT, " +
                "descricao TEXT, " +
                "resource INTEGER, " +
                "categoria TEXT," +
                "valor FLOAT)";
        return sql;
    }

    public static List<Item> getItensIniciais(){
        List<Item> itens = new ArrayList<>();

        Item item = new Item();
        item.titulo = "Massa Carbonara";
        item.descricao = "Massa feita com muito queijo e amor";
        item.resource = R.drawable.massa_carbonara;
        item.categoria = "prato";
        item.valor = 28.90;

        itens.add(item);

        item = new Item();
        item.titulo = "Costela do Cheff";
        item.descricao = "Costela com o molho saboroso do MasterChef";
        item.resource = R.drawable.costela_cheff;
        item.categoria = "prato";
        item.valor = 18.54;

        itens.add(item);

        return itens;
    }
}
