package com.canplimplam.superhector.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.canplimplam.superhector.R;
import com.canplimplam.superhector.modelo.Articulo;
import com.canplimplam.superhector.modelo.Catalogo;
import com.canplimplam.superhector.modelo.Tipo;

import java.util.ArrayList;
import java.util.List;

public class BusquedaCatalogoAdapter extends BaseAdapter {

    private LayoutInflater inflater = null;
    private List<Articulo> articulosFiltrados;
    private Context contexto;

    public BusquedaCatalogoAdapter(Context contexto, List<Articulo> articulos){
        this.contexto = contexto;
        articulosFiltrados = articulos;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    public void setArticulos(List<Articulo> lista){
        articulosFiltrados = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.row_model_articulo_busqueda_catalogo, null);

        //Recoger todas las vistas de ese layout..
        TextView nombreArticulo = (TextView) vista.findViewById(R.id.idNombreArticuloBusquedaCatalogo);
        TextView puntosArticulo = (TextView) vista.findViewById(R.id.idPuntosArticuloBusquedaCatalogo);

        Articulo articulo = articulosFiltrados.get(position);
        nombreArticulo.setText(articulo.getNombre());
        puntosArticulo.setText(String.valueOf(articulo.getPuntos()));

        return vista;
    }

    @Override
    public int getCount() {
        return articulosFiltrados.size();
    }

    @Override
    public Object getItem(int position) {
        return articulosFiltrados.get(position);
    }

    @Override
    public long getItemId(int position) {
        return articulosFiltrados.get(position).getCodigo();
    }
}
