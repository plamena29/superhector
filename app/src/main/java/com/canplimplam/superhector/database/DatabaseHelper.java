package com.canplimplam.superhector.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.canplimplam.superhector.modelo.Articulo;
import com.canplimplam.superhector.modelo.Tipo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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

    public Articulo createArticuloEnCatalogo(Articulo articulo){

        //Montamos contentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, articulo.getNombre());
        contentValues.put(COL_3, articulo.getPuntos());
        contentValues.put(COL_4, articulo.getTipo().toString());

        long resultado = db.insert(CATALOGO_TABLE, null, contentValues);
        //si resultado = -1 algo ha ido mal
        //si resultado >= 0 nos indica el número de registros afectados

        articulo.setCodigo((int) resultado);

        Log.d("**", articulo.toString());
        return resultado == -1 ? null: articulo;
    }

    public Articulo readArticuloEnCatalogo(int codigoArticulo){

        Articulo articulo = new Articulo();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT " + COL_1 + ", ")
                .append(COL_2 + ", ")
                .append(COL_3 + ", ")
                .append(COL_4)
                .append(" FROM ")
                .append(CATALOGO_TABLE)
                .append(" WHERE ")
                .append(COL_1 + " = " + codigoArticulo);

        Cursor cursor = db.rawQuery(sb.toString(), null);
        if(cursor != null && cursor.getCount() > 0){
            while (cursor.moveToNext()) {

                String nombre = cursor.getString(1);
                int puntos = cursor.getInt(2);
                Tipo tipo = Tipo.valueOf(cursor.getString(3));

                articulo.setCodigo(codigoArticulo);
                articulo.setNombre(nombre);
                articulo.setPuntos(puntos);
                articulo.setTipo(tipo);
            }
        }
        return articulo;
    }

    public Articulo updateArticuloEnCatalogo(Articulo articulo){
        int codigo = articulo.getCodigo();
        int nombreExistente = validarArticuloPorNombre(articulo.getNombre());

        //El articulo no existe y hay que crearlo:
        //1 - se crea nuevo en catalogo desde el Formulario
        if((codigo == -1) && (nombreExistente == -1)){
            Articulo articuloCreado = createArticuloEnCatalogo(articulo);
            return articuloCreado;
        }
        //Se conoce el código del articulo
        // 1- es una actualizacion desde el catalogo
        else if(codigo != -1){
            //Montamos contentValues
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_3, articulo.getPuntos());

            long resultado = db.update(CATALOGO_TABLE, contentValues, COL_1 + " = " + articulo.getCodigo(), null);

            Articulo articuloActualizado = readArticuloEnCatalogo(articulo.getCodigo());
            return articuloActualizado;
        }else{
            return articulo;
        }
    }

    public boolean deleteArticuloEnCatalogo(int codigoArticulo){
        long resultado = db.delete(CATALOGO_TABLE, COL_1 + " = " + codigoArticulo, null);
        return resultado <= 0 ? false: true;
    }

    //Devuelve el id de producto buscando por el nombre.
    //Si no existe, devuelve -1
    public int validarArticuloPorNombre(String nombreArticulo){
        int resultado = -1;

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT " + COL_1 + ", " + COL_2)
                .append(" FROM ")
                .append(CATALOGO_TABLE)
                .append(" WHERE ")
                .append(COL_2 + " = '" + nombreArticulo + "'");

        Cursor cursor = db.rawQuery(sb.toString(), null);
        if(cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                resultado = cursor.getInt(0);
            }
        }
        return resultado;
    }

    public List<Articulo> getAllCatalogo(){

        Cursor cursor = db.rawQuery("SELECT * FROM " + CATALOGO_TABLE, null);

        List<Articulo> articulos = new ArrayList<Articulo>();
        Map<String,Articulo> catalogo = new TreeMap<String,Articulo>();

        if(cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                int codigo = cursor.getInt(0);
                String nombre = cursor.getString(1);
                int puntos = cursor.getInt(2);
                Tipo tipo = Tipo.valueOf(cursor.getString(3));

                Articulo articulo = new Articulo(codigo, nombre, puntos, tipo);
                catalogo.put(nombre,articulo);
            }
        }

        Set<String> llaves = catalogo.keySet();
        for(String llave: llaves){
            Articulo a = catalogo.get(llave);
            articulos.add(a);
        }
        return articulos;
    }

    public List<Articulo> getAllCatalogoPorTipo(Tipo tipo){

        String[] campos = new String[]{COL_1, COL_2, COL_3, COL_4};

        Cursor cursor = db.query(CATALOGO_TABLE, campos, COL_4 + " LIKE ?", new String[]{"%" + tipo.toString() + "%"},null, null, null);

        List<Articulo> articulos = new ArrayList<Articulo>();
        Map<String,Articulo> catalogo = new TreeMap<String,Articulo>();

        if(cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                int codigo = cursor.getInt(0);
                String nombre = cursor.getString(1);
                int puntos = cursor.getInt(2);

                Articulo articulo = new Articulo(codigo, nombre, puntos, tipo);
                catalogo.put(nombre,articulo);
            }
        }

        Set<String> llaves = catalogo.keySet();
        for(String llave: llaves){
            Articulo a = catalogo.get(llave);
            articulos.add(a);
        }
        return articulos;
    }
}
