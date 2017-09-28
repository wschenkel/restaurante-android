package com.example.krauser.restauranteandroid.infra.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.krauser.restauranteandroid.Constants;
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

        inserirDadosPreDefinidosPedido(db);
        inserirDadosPreDefinidosItensPedido(db);
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

    private void inserirDadosPreDefinidosPedido(SQLiteDatabase db){

        String sql = "INSERT INTO " + Constants.PEDIDO_TABLE + " (nome, mesa, total, resumo, data) VALUES (%s, %s, %s, %s, %s)";
        db.execSQL(String.format(sql, "'Fulaninha'", 7, 567.98, "'Champagne, CINNAMON OBLIVION, BLOOMIN’ ONION ...'", "'09/07/17 - 13:30'"));

        db.execSQL(String.format(sql, "'Ciclaninho'", 18, 178.31, "'Absolut, Pave de limão, X salada ...'", "'30/08/17 - 09:45'"));

        db.execSQL(String.format(sql, "'Josué'", 3, 310.45, "'Vinho, Massa carbonara, Costelinha com molho barbecue ...'", "'11/04/17 - 12:31'"));

        db.execSQL(String.format(sql, "'João'", 13, 34.13, "'Caipirinha, GOLD COAST COCONUT SHRIMP, SPICY SHRIMP DIP  ...'", "'15/09/17 - 08:08'"));
    }

    private void inserirDadosPreDefinidosItensPedido(SQLiteDatabase db) {

        String sql = "INSERT INTO " + Constants.ITEM_TABLE + " (titulo, descricao, urlImagem) VALUES (%s, %s, %s)";

        db.execSQL(String.format(sql, "'Massa Carbonara'", "'Massa feita com muito queijo e amor'", "'http://www.seriouseats.com/recipes/assets_c/2017/02/20170210-vegan-carbonara-spaghetti-vicky-wasik-14-thumb-1500xauto-436613.jpg'"));
        db.execSQL(String.format(sql, "'Costela do Cheff'", "'Costela com o molho saboroso do MasterChef'", "'https://cafedelites.com/wp-content/uploads/2016/08/Slow-Cooker-BBQ-Spare-Ribs-68.jpg'"));
    }
}
