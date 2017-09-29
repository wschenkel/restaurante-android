package com.example.krauser.restauranteandroid.infra.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.krauser.restauranteandroid.Constants;
import com.example.krauser.restauranteandroid.infra.db.CreateDatabase;
import com.example.krauser.restauranteandroid.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemRepositorio {
    private CreateDatabase create;
    public ItemRepositorio(Context c){
        create = new CreateDatabase(c);
    }
    private String tbName = Constants.ITEM_TABLE;

    public List<Item> obterTodos(){

        List<Item> itens = new ArrayList<>();

        Cursor cursor;
        String[] campos =  {"id", "titulo", "descricao", "resource", "categoria", "valor"};
        SQLiteDatabase db = create.getReadableDatabase();
        cursor = db.query(Constants.ITEM_TABLE, campos, null, null, null, null, null);

        int count = 0;
        cursor.moveToFirst();
        while(count < cursor.getCount()){
            Item i = new Item();
            i.id = cursor.getInt(cursor.getColumnIndex("id"));
            i.titulo = cursor.getString(cursor.getColumnIndex("titulo"));
            i.descricao = cursor.getString(cursor.getColumnIndex("descricao"));
            i.resource = cursor.getInt(cursor.getColumnIndex("resource"));
            i.categoria = cursor.getString(cursor.getColumnIndex("categoria"));
            i.valor = cursor.getDouble(cursor.getColumnIndex("valor"));
            itens.add(i);
            cursor.moveToNext();
            count++;
        }
        db.close();

        return itens;
    }
}
