package com.example.krauser.restauranteandroid.infra.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.krauser.restauranteandroid.infra.db.CreateDatabase;
import com.example.krauser.restauranteandroid.model.Item;
import com.example.krauser.restauranteandroid.model.Pedido;
import com.example.krauser.restauranteandroid.util.Constants;
import com.example.krauser.restauranteandroid.util.Helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoRepositorio {
    private CreateDatabase create;

    public PedidoRepositorio(Context c){
        create = new CreateDatabase(c);
    }

    public void inserir(Pedido pedido) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = create.getWritableDatabase();

        pedido.data = Helper.dateToString(new Date());

        values.put("nome", pedido.nome);
        values.put("mesa", pedido.mesa);
        values.put("resumo", pedido.resumo);
        values.put("observacao", pedido.observacao);
        values.put("data", pedido.data);

        try{
            db.insertOrThrow(Constants.PEDIDO_TABLE, null, values);
            for(Item i : pedido.itens){
                values = new ContentValues();
                values.put("idItem", i.id);
                values.put("idPedido", pedido.id);
                db.insertOrThrow(Constants.ITEM_PEDIDO_TABLE, null, values);
            }
        }catch(Exception ex){
            throw new SQLException("Erro ao inserir registro - " + ex.getMessage());
        }finally {
            db.close();
        }
    }

    public List<Pedido> obterTodos(){
        List<Pedido> pedidos = new ArrayList<>();

        Cursor cursor;
        String[] campos =  {"id", "nome", "mesa", "total", "resumo", "data"};
        SQLiteDatabase db = create.getReadableDatabase();

        cursor = db.rawQuery("SELECT ip.idItem, ip.idPedido, p.nome, p.mesa, p.total, p.resumo, p.data, " +
                        "i.titulo, i.descricao, i.resource, i.categoria, i.valor " +
                        "FROM " + Constants.PEDIDO_TABLE + " AS p " +
                        "JOIN " + Constants.ITEM_PEDIDO_TABLE + " AS ip ON ip.idPedido = p.id " +
                        "JOIN " + Constants.ITEM_TABLE + " AS i ON i.id = ip.idItem " +
                        "ORDER BY p.id"
                , null);

        int count = 0;
        int id = 0;
        Pedido p = new Pedido();
        cursor.moveToFirst();
        while(count < cursor.getCount()){
            int idAtual = cursor.getInt(cursor.getColumnIndex("idPedido"));
            if(idAtual != id){
                p = new Pedido();
                p.id = cursor.getInt(cursor.getColumnIndex("idPedido"));
                p.nome = cursor.getString(cursor.getColumnIndex("nome"));
                p.mesa = cursor.getInt(cursor.getColumnIndex("mesa"));
                p.resumo = cursor.getString(cursor.getColumnIndex("resumo"));
                p.data = cursor.getString(cursor.getColumnIndex("data"));
                pedidos.add(p);
                id = idAtual;
            }

            Item i = new Item();
            i.id = cursor.getInt(cursor.getColumnIndex("idItem"));
            i.titulo = cursor.getString(cursor.getColumnIndex("titulo"));
            i.descricao = cursor.getString(cursor.getColumnIndex("descricao"));
            i.resource = cursor.getInt(cursor.getColumnIndex("resource"));
            i.categoria = cursor.getString(cursor.getColumnIndex("categoria"));
            i.valor = cursor.getDouble(cursor.getColumnIndex("valor"));
            p.itens.add(i);

            cursor.moveToNext();
            count++;
        }
        db.close();
        return pedidos;
    }
}
