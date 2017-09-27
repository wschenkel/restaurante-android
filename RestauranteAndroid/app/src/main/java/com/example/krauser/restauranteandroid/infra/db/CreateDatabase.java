package com.example.krauser.restauranteandroid.infra.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.krauser.restauranteandroid.model.ItemPedido;
import com.example.krauser.restauranteandroid.model.Pedido;

public class CreateDatabase extends SQLiteOpenHelper{

    private static final int VERSAO = 3;
    private static final String NOME_BANCO = "restaurante";

    private Context context;

    public CreateDatabase(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Pedido.getSqlCreateTable());
        db.execSQL(ItemPedido.getSqlCreateTable());
        inserirDadosPreDefinidos(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int ondVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS pedido");
        db.execSQL("DROP TABLE IF EXISTS item");
        onCreate(db);
    }

    private void inserirDadosPreDefinidos(SQLiteDatabase db){

        String sql = "INSERT INTO pedido (nome, mesa, total, resumo, data) VALUES (%s, %s, %s, %s, %s)";
        db.execSQL(String.format(sql, "'Fulaninha'", 7, 567.98, "'Champagne, CINNAMON OBLIVION, BLOOMIN’ ONION ...'", "'09/07/17 - 13:30'"));

        db.execSQL(String.format(sql, "'Ciclaninho'", 18, 178.31, "'Absolut, Pave de limão, X salada ...'", "'30/08/17 - 09:45'"));

        db.execSQL(String.format(sql, "'Josué'", 3, 310.45, "'Vinho, Massa carbonara, Costelinha com molho barbecue ...'", "'11/04/17 - 12:31'"));

        db.execSQL(String.format(sql, "'João'", 13, 34.13, "'Caipirinha, GOLD COAST COCONUT SHRIMP, SPICY SHRIMP DIP  ...'", "'15/09/17 - 08:08'"));
    }
}
