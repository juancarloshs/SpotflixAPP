package com.example.spotflyx;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class NavigationDrawerMovies extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    MaterialSearchView searchView;

    List<Pelicula> lstMovies = new ArrayList<Pelicula>();
    RecyclerViewAdapterMovies myAdapter;
    RecyclerView myrv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer_movies);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        new DatosServidor().execute();

        searchView = findViewById(R.id.search_view);

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void showMovies(View v) {
        int opciones = 1;
        Intent i = new Intent(this, MoviesActivity.class);
        i.putExtra("opciones", opciones);
        startActivity(i);
    }

    class DatosServidor extends AsyncTask {
        String responseText;

        @Override
        protected Object doInBackground(Object[] objects) {
            String path = "http://192.168.56.1:8080/WebServicess/rest/FilmRestService/peliculas";

            try {

                URL url = new URL(path);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(150000);
                conn.setConnectTimeout(150000);
                conn.setRequestMethod("GET");
                int responseCode = conn.getResponseCode();
                String responseMessage = conn.getResponseMessage();


                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String output;
                    StringBuffer response = new StringBuffer();

                    while ((output = in.readLine()) != null) {
                        response.append(output);
                    }
                    in.close();

                    responseText = response.toString();

                    JSONArray jsonarray = new JSONArray(responseText);
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        /*  et.setText(jsonobject.getString("Titulo"));*/
                        String des = jsonobject.getString("descripcion");
                        String dir = jsonobject.getString("director");
                        String gen = jsonobject.getString("genero");
                        int id = jsonobject.getInt("idPelicula");
                        String link = jsonobject.getString("link");
                        String por = jsonobject.getString("portada");
                        String tit = jsonobject.getString("titulo");


                        Pelicula obj = new Pelicula(id, tit, gen, des, dir, por, link);

                        lstMovies.add(obj);

                    }
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            myrv = findViewById(R.id.recyclerview_id_movies);
            myAdapter = new RecyclerViewAdapterMovies(getApplicationContext(), lstMovies);
            myrv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
            myrv.setAdapter(myAdapter);

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer_movies, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_comedy) {
            // Handle the camera action
            Toast toast1 = Toast.makeText(getApplicationContext(), "Toast por defecto", Toast.LENGTH_SHORT);
            toast1.show();
        } else if (id == R.id.nav_drama) {
            Toast toast1 = Toast.makeText(getApplicationContext(), "Toast por defecto", Toast.LENGTH_SHORT);
            toast1.show();
        } else if (id == R.id.nav_terror) {

        } else if (id == R.id.nav_thriller) {

        } else if (id == R.id.nav_science_fiction) {

        } else if (id == R.id.nav_romantic) {

        } else if (id == R.id.nav_adventures) {

        } else if (id == R.id.nav_musical) {

        } else if (id == R.id.nav_war) {

        } else if (id == R.id.nav_all_movies) {

        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
