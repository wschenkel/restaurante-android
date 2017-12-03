package com.example.krauser.restauranteandroid.infra.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.krauser.restauranteandroid.infra.db.CreateDatabase;
import com.example.krauser.restauranteandroid.model.Item;
import com.example.krauser.restauranteandroid.util.Constants;
import com.example.krauser.restauranteandroid.util.Helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemRepositorio {
    private CreateDatabase create;
    public ItemRepositorio(Context c){
        create = new CreateDatabase(c);
    }

    public List<Item> obterTodos(){

        List<Item> itens = new ArrayList<>();

        Cursor cursor;
        String[] campos =  {"id", "titulo", "descricao", "imagem", "categoria", "valor"};
        SQLiteDatabase db = create.getReadableDatabase();
        cursor = db.query(Constants.ITEM_TABLE, campos, null, null, null, null, null);

        int count = 0;
        cursor.moveToFirst();
        while(count < cursor.getCount()){
            Item i = new Item();
            i.id = cursor.getInt(cursor.getColumnIndex("id"));
            i.titulo = cursor.getString(cursor.getColumnIndex("titulo"));
            i.descricao = cursor.getString(cursor.getColumnIndex("descricao"));
            i.imagem = cursor.getString(cursor.getColumnIndex("imagem"));
            i.categoria = cursor.getString(cursor.getColumnIndex("categoria"));
            i.valor = cursor.getDouble(cursor.getColumnIndex("valor"));
            itens.add(i);
            cursor.moveToNext();
            count++;
        }
        db.close();

        return itens;
    }

    public void inserir(Item item){
        ContentValues values = new ContentValues();
        SQLiteDatabase db = create.getWritableDatabase();

        values.put("id", item.id);
        values.put("titulo", item.titulo);
        values.put("descricao", item.descricao);
        values.put("imagem", item.imagem);
        values.put("categoria", item.categoria);
        values.put("valor", item.valor);

        db.insertOrThrow(Constants.ITEM_TABLE, null, values);
    }

    public void removerTodos(){
        SQLiteDatabase db = create.getWritableDatabase();
        db.execSQL("delete from " + Constants.ITEM_TABLE);
    }
}