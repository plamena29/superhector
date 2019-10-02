package com.canplimplam.superhector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.canplimplam.superhector.customviews.MyValueSelector;

public class NuevoArticuloActivity extends AppCompatActivity {

    TextView editNombre;
    MyValueSelector editPuntos;
    RadioGroup editTipo;
    RadioButton tipoActividad;
    RadioButton tipoRecompensa;
    RadioButton tipoCastigo;
    Button botonGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_articulo);
        Log.d("**", "despues de constructor");
        editNombre = (TextView) findViewById(R.id.idNombreNuevoArticulo);
        editPuntos = (MyValueSelector) findViewById(R.id.idPuntosNuevoArticulo);
        editTipo = (RadioGroup) findViewById(R.id.idTipoNuevoArticulo);
        tipoActividad = (RadioButton) findViewById(R.id.idTipoActividadNuevoArticulo);
        tipoRecompensa = (RadioButton) findViewById(R.id.idTipoRecompensaNuevoArticulo);
        tipoCastigo = (RadioButton) findViewById(R.id.idTipoCastigoNuevoArticulo);
        botonGuardar = (Button) findViewById(R.id.idBotonGuardarNuevoArticulo);
        Log.d("**", "encontrados views en formulario");
        editPuntos.setValor(0);
        Log.d("**", "despues de set valor");

    }
}
