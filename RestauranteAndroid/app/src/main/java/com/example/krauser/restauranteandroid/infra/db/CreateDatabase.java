package com.example.krauser.restauranteandroid.infra.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.krauser.restauranteandroid.infra.model.Item;

public class CreateDatabase extends SQLiteOpenHelper{

    public CreateDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(Item.getCreateTableSql());
        }catch(Exception ex){
            String msg = ex.getMessage();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
