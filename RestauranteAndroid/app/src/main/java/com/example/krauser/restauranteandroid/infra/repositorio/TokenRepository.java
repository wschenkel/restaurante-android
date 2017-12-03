package com.example.krauser.restauranteandroid.infra.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.krauser.restauranteandroid.infra.db.CreateDatabase;
import com.example.krauser.restauranteandroid.util.Constants;

public class TokenRepository {

    public static String currentToken;
    private CreateDatabase create;

    public TokenRepository(Context c) {
        create = new CreateDatabase(c);
    }

    public String getStoredToken() {
        String token = null;

        Cursor cursor;
        SQLiteDatabase db = create.getReadableDatabase();

        cursor = db.rawQuery("SELECT token from " + Constants.TOKEN_TABLE
                , null);

        cursor.moveToFirst();
        if(cursor.getCount() > 0)
            token = cursor.getString(cursor.getColumnIndex("token"));
        db.close();
        return token;
    }

    public void update(String token) throws SQLException {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = create.getWritableDatabase();
        values.put("token", token);

        try {
            db.execSQL("delete from " + Constants.TOKEN_TABLE);
            db.insertOrThrow(Constants.TOKEN_TABLE, null, values);
        } catch (Exception ex) {
            throw new SQLException("Erro ao inserir registro - " + ex.getMessage());
        } finally {
            db.close();
        }
    }
}
