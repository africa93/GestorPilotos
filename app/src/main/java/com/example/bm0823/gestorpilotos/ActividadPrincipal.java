package com.example.bm0823.gestorpilotos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActividadPrincipal extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        AlmacenPilotos db = new AlmacenPilotos(getApplicationContext());


        ArrayList<Piloto> pilotos =db.getAll();
        PilotoAdapter pilotoAdapter = new PilotoAdapter(this,pilotos);
        final ListView lvPilotos = (ListView)findViewById(R.id.listadoPiloto);
        lvPilotos.setAdapter(pilotoAdapter);

        lvPilotos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String opcionElegida = lvPilotos.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), opcionElegida, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actividad_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart(){
        super.onStart();
        AlmacenPilotos db = new AlmacenPilotos(getApplicationContext());

        //db.add(new Piloto(4, "P1",1,"moto1", true));
        //db.add(new Piloto(2, "P2",1,"moto2", true));
        //db.add(new Piloto(3, "P3",1,"moto3", true));

        //TO DO recuperar todos los pilotos
        ArrayList<Piloto> pilotos =db.getAll();

        //TO DO mostrar pilotos en lista
        for(int i=0; i<pilotos.size(); i++){
            Log.i("Piloto: ", pilotos.get(i).toString());
        }

        PilotoAdapter pilotoAdapter = new PilotoAdapter(this,pilotos);
        ListView lvPilotos = (ListView)findViewById(R.id.listadoPiloto);
        lvPilotos.setAdapter(pilotoAdapter);
    }
}
