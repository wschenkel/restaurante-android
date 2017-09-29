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

        inserirItensPreDefinidos(db);
        inserirPedidosPreDefinidos(db);
        bindPedidoItem(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int ondVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.ITEM_PEDIDO_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.ITEM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.PEDIDO_TABLE);
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

    private void inserirPedidosPreDefinidos(SQLiteDatabase db){
        String sql = "INSERT INTO " + Constants.PEDIDO_TABLE + " (nome, mesa, total, resumo, data) VALUES ('%s', %s, %s, '%s', '%s')";
        for(Pedido p : Pedido.getPedidosIniciais())
            db.execSQL(String.format(sql, p.nome, p.mesa, p.total, p.resumo, p.data));
    }

    private void inserirItensPreDefinidos(SQLiteDatabase db) {
        String sql = "INSERT INTO " + Constants.ITEM_TABLE + " (titulo, descricao, resource, categoria, valor) VALUES ('%s', '%s', %s, '%s', %s)";
        for(Item item : Item.getItensIniciais())
            db.execSQL(String.format(sql, item.titulo, item.descricao, item.resource, item.categoria, item.valor));
    }

    private void bindPedidoItem(SQLiteDatabase db){
        db.execSQL("INSERT INTO " + Constants.ITEM_PEDIDO_TABLE + "(idPedido, idItem) VALUES (1,1)");
        db.execSQL("INSERT INTO " + Constants.ITEM_PEDIDO_TABLE + "(idPedido, idItem) VALUES (1,1)");
        db.execSQL("INSERT INTO " + Constants.ITEM_PEDIDO_TABLE + "(idPedido, idItem) VALUES (1,2)");
        db.execSQL("INSERT INTO " + Constants.ITEM_PEDIDO_TABLE + "(idPedido, idItem) VALUES (2,1)");
        db.execSQL("INSERT INTO " + Constants.ITEM_PEDIDO_TABLE + "(idPedido, idItem) VALUES (2,2)");
    }
}
