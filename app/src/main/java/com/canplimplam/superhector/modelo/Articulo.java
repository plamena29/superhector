package com.canplimplam.superhector.modelo;

public class Articulo {
    private int codigo;
    private String nombre;
    private int puntos;
    private Tipo tipo;

    public Articulo(){

    }

    public Articulo(int codigo, String nombre, int puntos, Tipo tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.puntos = puntos;
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Articulo articulo = (Articulo) o;

        return nombre.equals(articulo.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.hashCode();
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", puntos=" + puntos +
                ", tipo=" + tipo +
                '}';
    }
}
