package com.canplimplam.superhector.services;

import com.canplimplam.superhector.modelo.Articulo;
import com.canplimplam.superhector.modelo.Tipo;

import java.util.List;

public interface CatalogoService {

    //CRUD
    public Articulo createArticuloEnCatalogo(Articulo articulo);
    public Articulo readArticuloEnCatalogo(int codigoArticulo);
    public Articulo updateArticuloEnCatalogo(Articulo articulo);
    public boolean deleteArticuloEnCatalogo(int codigoArticulo);
    public int validarArticuloPorNombre(String nombreArticulo);

    //Filtros
    public List<Articulo> getAllCatalogoPorTipo(Tipo tipo);
    public List<Articulo> getByTextCatalogo(String texto, Tipo tipo);

}
