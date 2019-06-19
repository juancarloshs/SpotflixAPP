package com.example.spotflyx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class OptionsActivity extends AppCompatActivity {
    Button btnpeliculas, btnMusica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        btnpeliculas = (Button) findViewById(R.id.btnPeliculas);
        btnMusica = findViewById(R.id.btnMusica);
    }

    public void MostrarPeliculas(View v) {

        Intent i = new Intent(this, NavigationDrawerMovies.class);
        startActivity(i);
    }

    public void MostrarAlbum(View v) {

        Intent i = new Intent(this, NavigationDrawerMusic.class);
        startActivity(i);
    }
}
