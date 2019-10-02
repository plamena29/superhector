package com.canplimplam.superhector.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.canplimplam.superhector.modelo.Articulo;

import java.text.SimpleDateFormat;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Base de datos
    public static final String DATABASE_NAME = "superhector.db";

    //Tablas
    public static final String CATALOGO_TABLE = "CATALOGO";

    //Columnas
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NOMBRE";
    public static final String COL_3 = "PUNTOS";
    public static final String COL_4 = "TIPO";

    public final SQLiteDatabase db = this.getWritableDatabase();

    private static final SimpleDateFormat SDF_AMERICA = new SimpleDateFormat("yyyy/MM/dd");

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crea la tabla del Catalogo
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ")
                .append(CATALOGO_TABLE).append(" (")
                .append(COL_1).append(" INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append(COL_2).append(" TEXT NOT NULL,")
                .append(COL_3).append(" INTEGER NOT NULL,")
                .append(COL_4).append(" TEXT)");

        String strDDL = sb.toString();
        db.execSQL(strDDL);
        //mejoraFase1(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //GESTOR PARA EL CATALOGO

    public Articulo crearArticuloEnDespensa(Articulo articulo){

        //Montamos contentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, articulo.getNombre());
        contentValues.put(COL_3, articulo.getPuntos());
        contentValues.put(COL_4, articulo.getTipo().toString());

        long resultado = db.insert(CATALOGO_TABLE, null, contentValues);
        //si resultado = -1 algo ha ido mal
        //si resultado >= 0 nos indica el número de registros afectados

        articulo.setCodigo((int) resultado);

        return resultado == -1 ? null: articulo;
    }

}
