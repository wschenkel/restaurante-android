package com.example.krauser.restauranteandroid.infra.model;

/**
 * Created by wschenkel on 24/09/17.
 */

public class Pedido {
    public static String getCreateTableSql(){
        String sql = "CREATE TABLE Pedido (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "idItemPedido INTEGER " +
                "nomeResponsavel TEXT, " +
                "numeroDaMesa INTEGER, " +
                "valorTotal FLOAT, " +
                "resumo TEXT, " +
                "observacao TEXT)";
        return sql;
    }
}


