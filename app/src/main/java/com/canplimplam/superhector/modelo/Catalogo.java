package com.canplimplam.superhector.modelo;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Catalogo {
    private Map<String,Articulo> articulos;

    public Catalogo(){

    }

    public Catalogo(List<Articulo> lista) {
        articulos = new TreeMap<>();
        for(Articulo a: lista){
            articulos.put(a.getNombre(), a);
        }
    }

    public List<Articulo> getArticulos() {
        List<Articulo> lista = new ArrayList<>();
        Set<String> llaves = articulos.keySet();
        for(String llave: llaves){
            lista.add(articulos.get(llave));
        }
        return lista;
    }

    public Map<String,Articulo> getMapa(){
        return articulos;
    }

    @Override
    public String toString() {
        return "Catalogo{" +
                "articulos=" + articulos +
                '}';
    }
}
