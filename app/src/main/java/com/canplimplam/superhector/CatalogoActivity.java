package com.canplimplam.superhector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.canplimplam.superhector.adaptadores.CatalogoAdapter;
import com.canplimplam.superhector.customviews.MyValueSelector;
import com.canplimplam.superhector.modelo.Articulo;
import com.canplimplam.superhector.modelo.Catalogo;
import com.canplimplam.superhector.modelo.Tipo;
import com.canplimplam.superhector.services.CatalogoServices;
import com.canplimplam.superhector.services.CatalogoServicesSQLite;

import java.util.List;

public class CatalogoActivity extends AppCompatActivity {

    RadioGroup selectTipo;
    RadioButton tipoActividad;
    RadioButton tipoRecompensa;
    RadioButton tipoCastigo;
    ListView resultado;
    Button botonGuardar;

    //Services
    private CatalogoServices catalogoServices;

    //Variables de ayuda
    Tipo tipo;
    Catalogo catalogo;
    CatalogoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        selectTipo = (RadioGroup) findViewById(R.id.idTipoCatalogo);
        tipoActividad = (RadioButton) findViewById(R.id.idTipoActividadCatalogo);
        tipoRecompensa = (RadioButton) findViewById(R.id.idTipoRecompensaCatalogo);
        tipoCastigo = (RadioButton) findViewById(R.id.idTipoCastigoCatalogo);
        resultado = (ListView) findViewById(R.id.idCatalogo);
        botonGuardar = (Button) findViewById(R.id.idBotonGuardarCatalogo);

        //Inicializamos con datos de tipo Actividad
        catalogoServices = new CatalogoServicesSQLite(this);
        tipo = Tipo.valueOf("ACTIVIDAD");
        tipoActividad.setChecked(true);
        catalogo = new Catalogo(catalogoServices.getAllCatalogo());
        adapter = new CatalogoAdapter(this, catalogo);
        resultado.setAdapter(adapter);

        //Listeners
        selectTipo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.idTipoActividadCatalogo){
                    tipo = Tipo.valueOf("ACTIVIDAD");
                } else if(checkedId == R.id.idTipoRecompensaCatalogo){
                    tipo = Tipo.valueOf("RECOMPENSA");
                }else{
                    tipo = Tipo.valueOf("CASTIGO");
                }
                adapter.setArticulosFiltrados(tipo);
                adapter.notifyDataSetChanged();
            }
        });

        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Articulo> catalogoActual = catalogoServices.getAllCatalogo();
                List<Articulo> catalogoActualizado = adapter.getAllCatalogo();

                //Eliminamos de SQLite los productos que no estén en el Map del adaptador
                for(Articulo articuloOriginal: catalogoActual){
                    boolean encontrado = false;
                    for(Articulo articuloMap: catalogoActualizado){
                        if(articuloOriginal.getNombre().equals(articuloMap.getNombre())){
                            encontrado = true;
                        }
                    }
                    if(!encontrado){
                        catalogoServices.deleteArticuloEnCatalogo(articuloOriginal.getCodigo());
                    }
                }

                //Guardamos/actualizamos los productos que hay en el Map
                for(Articulo articuloMap: catalogoActualizado){
                    catalogoServices.updateArticuloEnCatalogo(articuloMap);
                }
            }
        });
    }
}
