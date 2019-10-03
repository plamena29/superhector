package com.canplimplam.superhector.services;

import android.content.Context;

import com.canplimplam.superhector.database.DatabaseHelper;
import com.canplimplam.superhector.modelo.Articulo;
import com.canplimplam.superhector.modelo.Tipo;

import java.util.List;

public class CatalogoServicesSQLite implements CatalogoServices {

    private Context contexto;
    private DatabaseHelper myDB;

    public CatalogoServicesSQLite(Context context){
        this.contexto = context;
        myDB = new DatabaseHelper(contexto);
    }

    @Override
    public Articulo createArticuloEnCatalogo(Articulo articulo) {
        return myDB.createArticuloEnCatalogo(articulo);
    }

    @Override
    public Articulo readArticuloEnCatalogo(int codigoArticulo) {
        return myDB.readArticuloEnCatalogo(codigoArticulo);
    }

    @Override
    public Articulo updateArticuloEnCatalogo(Articulo articulo) {
        return myDB.updateArticuloEnCatalogo(articulo);
    }

    @Override
    public boolean deleteArticuloEnCatalogo(int codigoArticulo) {
        return false;
    }

    @Override
    public int validarArticuloPorNombre(String nombreArticulo) {
        return myDB.validarArticuloPorNombre(nombreArticulo);
    }

    @Override
    public List<Articulo> getAllCatalogoPorTipo(Tipo tipo) {
        return null;
    }

    @Override
    public List<Articulo> getByTextCatalogo(String texto, Tipo tipo) {
        return null;
    }
}
