package com.example.spotflyx;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MusicActivityList extends AppCompatActivity {
    ArrayList<Cancion>listacanciones=new ArrayList<Cancion>();
    final ArrayList<Cancion > NombreCanciones=new ArrayList<Cancion>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Titulo");
        String Portada = intent.getExtras().getString("Portada");
        String Artista = intent.getExtras().getString("Artista");
        listacanciones = (ArrayList<Cancion>) intent.getSerializableExtra("Canciones");

        final ArrayList<String> NombreCanciones = cargarNombreCanciones(listacanciones);

        for (int i = 0; i < NombreCanciones.size(); i++) {

            ListView lvstring = (ListView) findViewById(R.id.listViewMusic);
            lvstring.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, NombreCanciones));

            lvstring.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    /*Prueba*/     Toast.makeText(getBaseContext(),NombreCanciones.get(position)+listacanciones.get(position).getLink(),Toast.LENGTH_SHORT).show();

                }
            });
        }

    }

    public ArrayList<String> cargarNombreCanciones(ArrayList<Cancion> list)
    {
        ArrayList<String> NombreCanciones=new ArrayList<String>();
        System.out.println("-----"+list.toString());
        for (int i = 0; i < list.size(); i++)
        {

            NombreCanciones.add(list.get(i).getNombreCancion());
        }

        return NombreCanciones;
    }
}
