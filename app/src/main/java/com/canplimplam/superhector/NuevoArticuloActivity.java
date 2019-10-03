package com.canplimplam.superhector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.canplimplam.superhector.customviews.MyValueSelector;
import com.canplimplam.superhector.modelo.Articulo;
import com.canplimplam.superhector.modelo.Tipo;
import com.canplimplam.superhector.services.CatalogoServices;
import com.canplimplam.superhector.services.CatalogoServicesSQLite;

public class NuevoArticuloActivity extends AppCompatActivity {

    TextView editNombre;
    MyValueSelector editPuntos;
    RadioGroup editTipo;
    RadioButton tipoActividad;
    RadioButton tipoRecompensa;
    RadioButton tipoCastigo;
    Button botonGuardar;
    Tipo tipo;
    private CatalogoServices catalogoServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_articulo);

        editNombre = (TextView) findViewById(R.id.idNombreNuevoArticulo);
        editPuntos = (MyValueSelector) findViewById(R.id.idPuntosNuevoArticulo);
        editTipo = (RadioGroup) findViewById(R.id.idTipoNuevoArticulo);
        tipoActividad = (RadioButton) findViewById(R.id.idTipoActividadNuevoArticulo);
        tipoRecompensa = (RadioButton) findViewById(R.id.idTipoRecompensaNuevoArticulo);
        tipoCastigo = (RadioButton) findViewById(R.id.idTipoCastigoNuevoArticulo);
        botonGuardar = (Button) findViewById(R.id.idBotonGuardarNuevoArticulo);

        catalogoServices = new CatalogoServicesSQLite(this);

        //Inicializamos variables a Articulo de tipo ACTIVIDAD
        editPuntos.setValor(5);
        tipoActividad.setChecked(true);
        tipo = Tipo.valueOf("ACTIVIDAD");

        editTipo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.idTipoActividadNuevoArticulo){
                    editPuntos.setValor(5);
                    tipo = Tipo.valueOf("ACTIVIDAD");
                } else if(checkedId == R.id.idTipoRecompensaNuevoArticulo){
                    editPuntos.setValor(-300);
                    tipo = Tipo.valueOf("RECOMPENSA");
                }else{
                    editPuntos.setValor(-5);
                    tipo = Tipo.valueOf("CASTIGO");
                }
            }
        });

        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean error = false;
                if(editNombre.getText().toString().equals("")){
                    error = true;
                }
                if(tipo.equals("ACTIVIDAD") && editPuntos.getValor() <= 0){
                    error = true;
                }
                if((tipo.equals("RECOMPENSA") || tipo.equals("CASTIGO"))&& editPuntos.getValor() >= 0){
                    error = true;
                }

                if(!error){
                    String nombre = editNombre.getText().toString();
                    int puntos = editPuntos.getValor();
                    Articulo articulo = new Articulo(-1, nombre, puntos, tipo);
                    catalogoServices.updateArticuloEnCatalogo(articulo);
                }
            }
        });
    }
}
