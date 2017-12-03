package com.example.krauser.restauranteandroid.infra.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.krauser.restauranteandroid.util.Constants;
import com.example.krauser.restauranteandroid.model.Item;
import com.example.krauser.restauranteandroid.model.Pedido;

public class CreateDatabase extends SQLiteOpenHelper{

    private Context context;

    public CreateDatabase(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Pedido.getSqlCreateTable());
        db.execSQL(Item.getSqlCreateTable());
        db.execSQL(getCreateTableItemPedido());
        db.execSQL("CREATE TABLE token (token TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int ondVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.ITEM_PEDIDO_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.ITEM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.PEDIDO_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TOKEN_TABLE);
        onCreate(db);
    }

    private String getCreateTableItemPedido(){
        String sql = "CREATE TABLE " + Constants.ITEM_PEDIDO_TABLE + " ( " +
                "idItem INTEGER NOT NULL, " +
                "idPedido INTEGER NOT NULL, " +
                "FOREIGN KEY (idItem) REFERENCES " + Constants.ITEM_TABLE + "(id)," +
                "FOREIGN KEY (idPedido) REFERENCES " + Constants.PEDIDO_TABLE + "(id))";
        return sql;
    }
}
