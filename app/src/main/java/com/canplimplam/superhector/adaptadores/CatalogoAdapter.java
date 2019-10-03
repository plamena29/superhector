package com.canplimplam.superhector.adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.canplimplam.superhector.R;
import com.canplimplam.superhector.customviews.MyValueSelector;
import com.canplimplam.superhector.modelo.Articulo;
import com.canplimplam.superhector.modelo.Catalogo;
import com.canplimplam.superhector.modelo.Tipo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CatalogoAdapter extends BaseAdapter {

    private LayoutInflater inflater = null;
    private Catalogo catalogo;  //Todos los registros de SQLite donde se almacenaran los cambios
    private List<Articulo> articulosFiltrados; //Solo los mostrados según filtro por Tipo
    private Context contexto;

    public CatalogoAdapter(Context contexto, Catalogo catalogo){
        this.contexto = contexto;
        this.catalogo = catalogo;
        articulosFiltrados = new ArrayList<>();
        setArticulosFiltrados(Tipo.valueOf("ACTIVIDAD"));

        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    public void setArticulosFiltrados(Tipo tipo){
        articulosFiltrados.clear();
        for(Articulo a: catalogo.getArticulos()){
            if(a.getTipo().equals(tipo)){
                articulosFiltrados.add(a);
            }
        }
    }

    public List<Articulo> getAllCatalogo(){
        List<Articulo> articulosActualizados = new ArrayList<>();
        Set<String> llaves = catalogo.getMapa().keySet();
        for(String llave: llaves){
            articulosActualizados.add(catalogo.getMapa().get(llave));
        }
        return articulosActualizados;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.row_model_articulo_catalogo, null);

        //Recoger todas las vistas de ese layout..
        final ImageView imageBorrarArticulo = (ImageView) vista.findViewById(R.id.idImageBorrarArticuloEnCatalogo);
        final TextView nombreArticulo = (TextView) vista.findViewById(R.id.idNombreArticuloEnCatalogo);
        final MyValueSelector puntosArticulo = (MyValueSelector) vista.findViewById(R.id.idPuntosArticuloEnCatalogo);

        Articulo articulo = articulosFiltrados.get(position);
        imageBorrarArticulo.setImageResource(R.drawable.botoneliminar);
        nombreArticulo.setText(articulo.getNombre());
        puntosArticulo.setValor(articulo.getPuntos());

        //Listeners
        imageBorrarArticulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catalogo.getMapa().remove(articulosFiltrados.get(position).getNombre());
                articulosFiltrados.remove(position);
                notifyDataSetChanged();
            }
        });

        puntosArticulo.setMyValueSelectorListener(new MyValueSelector.MyValueSelectorListener() {
            @Override
            public void onDataLoaded(MyValueSelector mvs) {
                Articulo articulo = articulosFiltrados.get(position);
                articulo.setPuntos(mvs.getValor());
                catalogo.getMapa().put(articulosFiltrados.get(position).getNombre(), articulo);
                articulosFiltrados.get(position).setPuntos(mvs.getValor());
            }
        });
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
