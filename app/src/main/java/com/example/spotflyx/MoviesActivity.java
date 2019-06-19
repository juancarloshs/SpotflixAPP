package com.example.spotflyx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MoviesActivity extends AppCompatActivity {
    private TextView tvtitle, tvdescription, tvcategory;
    private ImageView img;
    String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        tvtitle = findViewById(R.id.txttitle);
        tvdescription = findViewById(R.id.txtDesc);
        tvcategory = findViewById(R.id.txtCat);
        img = findViewById(R.id.bookthumbnail);


        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Titulo");
        String Description = intent.getExtras().getString("Descripcion");
        String image = intent.getExtras().getString("Portada");
        String gen = intent.getExtras().getString("Genero");
        link = intent.getExtras().getString("Link");

        tvtitle.setText(Title);
        tvdescription.setText(Description);
        Picasso.with(this).load(image).into(img);
        tvcategory.setText(gen);
    }

    public void ReproducirPelicula(View v) {

        Intent i = new Intent(this, MoviePlayer.class);
        i.putExtra("link", link);
        startActivity(i);
    }
}
