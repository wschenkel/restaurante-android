package com.example.krauser.restauranteandroid.infra.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.krauser.restauranteandroid.Constants;
import com.example.krauser.restauranteandroid.infra.db.CreateDatabase;
import com.example.krauser.restauranteandroid.model.Pedido;

import java.text.SimpleDateFormat;
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

        pedido.data = new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(new Date());

        values.put("nome", pedido.nome);
        values.put("mesa", pedido.mesa);
        values.put("total", pedido.total);
        values.put("resumo", pedido.resumo);
        values.put("observacao", pedido.observacao);
        values.put("data", pedido.data);

        try{
            db.insertOrThrow(Constants.PEDIDO_TABLE, null, values);
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
        cursor = db.query(Constants.PEDIDO_TABLE, campos, null, null, null, null, null, null);

        int count = 0;
        cursor.moveToFirst();
        while(count < cursor.getCount()){
            Pedido p = new Pedido();
            int index = cursor.getColumnIndex("id");
            p.id = cursor.getInt(cursor.getColumnIndex("id"));
            p.nome = cursor.getString(cursor.getColumnIndex("nome"));
            p.mesa = cursor.getInt(cursor.getColumnIndex("mesa"));
            p.total = cursor.getDouble(cursor.getColumnIndex("total"));
            p.resumo = cursor.getString(cursor.getColumnIndex("resumo"));
            p.data = cursor.getString(cursor.getColumnIndex("data"));
            pedidos.add(p);
            cursor.moveToNext();
            count++;
        }
        db.close();
        return pedidos;
    }
}
