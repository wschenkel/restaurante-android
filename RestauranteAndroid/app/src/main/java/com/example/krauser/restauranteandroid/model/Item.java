package com.example.krauser.restauranteandroid.model;

import com.example.krauser.restauranteandroid.util.Constants;
import com.example.krauser.restauranteandroid.R;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Item implements Serializable {

    public int id;
    public String titulo;
    public String descricao;
    public String resource;
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
        item.resource = "http://brcdn.ar-cdn.com/recipes/originalxl/ebabbcdd-d246-48e5-abe9-f6496f6fc8ba.jpg";
        item.categoria = "prato";
        item.valor = 28.93;

        itens.add(item);

        item = new Item();
        item.titulo = "Costela do Cheff";
        item.descricao = "Costela com o molho saboroso do MasterChef";
        item.resource = "http://brcdn.ar-cdn.com/recipes/originalxl/ebabbcdd-d246-48e5-abe9-f6496f6fc8ba.jpg";
        item.categoria = "prato";
        item.valor = 18.54;

        itens.add(item);

        item = new Item();
        item.titulo = "Caipira Valilla";
        item.descricao = "Uma caipirinha de absolut vanilla nos sabores morango/kiwi/maracujá";
        item.resource = "http://brcdn.ar-cdn.com/recipes/originalxl/ebabbcdd-d246-48e5-abe9-f6496f6fc8ba.jpg";
        item.categoria = "bebida";
        item.valor = 37.84;

        itens.add(item);

        item = new Item();
        item.titulo = "Drink de whisky Valilla";
        item.descricao = "Um belo drink de jack daniels";
        item.resource = "http://brcdn.ar-cdn.com/recipes/originalxl/ebabbcdd-d246-48e5-abe9-f6496f6fc8ba.jpg";
        item.categoria = "bebida";
        item.valor = 29.38;

        itens.add(item);

        item = new Item();
        item.titulo = "Mousse";
        item.descricao = "Uma sobremesa de morango para complementar o almoço";
        item.resource = "http://brcdn.ar-cdn.com/recipes/originalxl/ebabbcdd-d246-48e5-abe9-f6496f6fc8ba.jpg";
        item.categoria = "sobremesa";
        item.valor = 8.46;

        itens.add(item);

        item = new Item();
        item.titulo = "Pudim";
        item.descricao = "Um doce essencial após uma boa refeição";
        item.resource = "http://brcdn.ar-cdn.com/recipes/originalxl/ebabbcdd-d246-48e5-abe9-f6496f6fc8ba.jpg";
        item.categoria = "sobremesa";
        item.valor = 6.32;

        itens.add(item);

        return itens;
    }
}
