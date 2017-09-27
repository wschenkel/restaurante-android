package com.example.krauser.restauranteandroid.model;

public class ItemPedido {
    public int id;
    public String urlImagem;
    public String titulo;
    public String descricao;

    public ItemPedido(){ }

    public ItemPedido(int id, String titulo, String descricao, String urlImagem){
        this.id = id;
        this.titulo = titulo;
        this.urlImagem = urlImagem;
        this.descricao = descricao;
    }

    public static String getSqlCreateTable(){
        String sql = "CREATE TABLE item (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, " +
                "descricao TEXT, " +
                "urlImagem TEXT)";
        return sql;
    }
}
