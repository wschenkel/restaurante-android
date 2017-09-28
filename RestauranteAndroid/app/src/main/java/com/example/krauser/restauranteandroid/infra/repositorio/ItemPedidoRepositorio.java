package com.example.krauser.restauranteandroid.infra.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.krauser.restauranteandroid.infra.db.CreateDatabase;
import com.example.krauser.restauranteandroid.model.ItemPedido;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemPedidoRepositorio {
    private CreateDatabase create;
    public ItemPedidoRepositorio(Context c){
        create = new CreateDatabase(c);
    }
    private String tbName = "itemPedido";

    public void inserir(ItemPedido item) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = create.getWritableDatabase();

        values.put("titulo", item.titulo);
        values.put("descricao", item.descricao);
        values.put("urlImagem", item.urlImagem);

        try{
            db.insertOrThrow(tbName, null, values);
        }catch(Exception ex){
            throw new SQLException("Erro ao inserir registro - " + ex.getMessage());
        }finally {
            db.close();
        }
    }

    public List<ItemPedido> obterTodos(){

        List<ItemPedido> itens = new ArrayList<>();

        Cursor cursor;
        String[] campos =  {"id", "titulo", "descricao", "urlImagem"};
        SQLiteDatabase db = create.getReadableDatabase();
        cursor = db.query("itemPedido", campos, null, null, null, null, null);

        int count = 0;
        cursor.moveToFirst();
        while(count < cursor.getCount()){
            ItemPedido i = new ItemPedido();
            int index = cursor.getColumnIndex("id");
            i.id = cursor.getInt(cursor.getColumnIndex("id"));
            i.titulo = cursor.getString(cursor.getColumnIndex("titulo"));
            i.descricao = cursor.getString(cursor.getColumnIndex("descricao"));
            i.urlImagem = cursor.getString(cursor.getColumnIndex("urlImagem"));
            itens.add(i);
            cursor.moveToNext();
            count++;
        }
        db.close();

        return itens;
    }
}
