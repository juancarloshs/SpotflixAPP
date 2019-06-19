package com.example.spotflyx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity {
    private TextView txttitle, txtgenre, txtartist;
    private ImageView img;
    String link;
    ArrayList<Cancion> list;
    Album obj;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        txttitle = findViewById(R.id.txttitle);
        txtgenre = findViewById(R.id.txtGen);
        txtartist = findViewById(R.id.txtArtista);
        img = findViewById(R.id.portada);

        Intent intent = getIntent();
        int idAlbum=intent.getExtras().getInt("idAlbum");
        String Title = intent.getExtras().getString("Titulo");
        String Genre = intent.getExtras().getString("Genero");
        String Portada = intent.getExtras().getString("Portada");
        String Artista = intent.getExtras().getString("Artista");
        list = (ArrayList<Cancion>) intent.getSerializableExtra("Canciones");

        obj = new Album(idAlbum, Title, Genre, Artista, Portada,  list);

        txttitle.setText(obj.getTitulo());
        txtgenre.setText(obj.getGenero());
        Picasso.with(this).load(obj.getPortada()).into(img);
        txtartist.setText(obj.getArtista());
    }
    public void MostrarCanciones(View v) {

        Intent i = new Intent(this, MusicActivityList.class);
        i.putExtra("Titulo", obj.getTitulo());
        i.putExtra("Portada", obj.getPortada());
        i.putExtra("Artista",obj.getArtista());
        i.putExtra("Canciones", list);
        startActivity(i);
    }
}
