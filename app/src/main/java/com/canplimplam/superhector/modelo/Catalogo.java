package com.canplimplam.superhector.modelo;

import java.util.List;

public class Catalogo {
    private List<Articulo> articulos;

    public Catalogo(){

    }

    public Catalogo(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    @Override
    public String toString() {
        return "Catalogo{" +
                "articulos=" + articulos +
                '}';
    }
}
