package com.canplimplam.superhector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button botonNuevoArticulo;
    Button botonCatalogo;
    Button botonMovimientos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.idTexto);
        textView.setText("Hola. Soy Hector.");

        botonNuevoArticulo = (Button) findViewById(R.id.idBotonNuevoArticulo);
        botonCatalogo = (Button) findViewById(R.id.idBotonCatalogo);
        botonMovimientos = (Button) findViewById(R.id.idBotonMovimientos);

        botonNuevoArticulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NuevoArticuloActivity.class);
                startActivity(intent);
            }
        });


        botonCatalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CatalogoActivity.class);
                startActivity(intent);
            }
        });

        botonMovimientos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MovimientosActivity.class);
                startActivity(intent);
            }
        });
    }
}
