package com.canplimplam.superhector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.canplimplam.superhector.adaptadores.BusquedaCatalogoAdapter;
import com.canplimplam.superhector.modelo.Articulo;
import com.canplimplam.superhector.services.CatalogoServices;
import com.canplimplam.superhector.services.CatalogoServicesSQLite;

import java.util.ArrayList;
import java.util.List;

public class MovimientosActivity extends AppCompatActivity {

    private EditText buscador;
    private ListView listaBusquedaCatalogo;
    private Button botonAddArticuloMovimientos;

    //Services
    private CatalogoServices catalogoServices;

    //Adaptadores
    private BusquedaCatalogoAdapter adaptadorBusqueda;

    //Variables de ayuda
    List<Articulo> resultadoBusqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimientos);

        //Seteamos los Services y variables
       catalogoServices = new CatalogoServicesSQLite(this);
       resultadoBusqueda = new ArrayList<Articulo>();

        //Identificamos los campos
        buscador = (EditText) findViewById(R.id.idBuscadorArticuloMovimientos);
        listaBusquedaCatalogo = (ListView) findViewById(R.id.idResultadoFiltroArticulosCatalogo);
        botonAddArticuloMovimientos = (Button) findViewById(R.id.idBotonAddMovimiento);

        //Seteamos adaptadores
        adaptadorBusqueda = new BusquedaCatalogoAdapter(this, resultadoBusqueda);
        listaBusquedaCatalogo.setAdapter(adaptadorBusqueda);

        //Funcionamiento del buscador en catalogo
        buscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(buscador.getText().toString().equals("")){
                    resultadoBusqueda.clear();
                }
                else{
                    resultadoBusqueda = catalogoServices.getByTextCatalogo(s.toString());

                }

                adaptadorBusqueda.setArticulos(resultadoBusqueda);
                adaptadorBusqueda.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
