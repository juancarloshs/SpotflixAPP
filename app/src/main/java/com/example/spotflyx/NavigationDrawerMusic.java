package com.example.spotflyx;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

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

public class NavigationDrawerMusic extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<Album> lstAlbums = new ArrayList<Album>();
    RecyclerViewAdapterMusic myAdapter;
    RecyclerView myrv;
    List<Cancion> listaCanciones = new ArrayList<Cancion>();
    MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer_music);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        searchView = findViewById(R.id.search_view);

        new DatosServidorMusica().execute();
    }

    public void showMusic(View v) {
        int opciones = 1;
        Intent i = new Intent(this, MusicActivity.class);
        i.putExtra("opciones", opciones);
        startActivity(i);
    }

    class DatosServidorMusica extends AsyncTask {

        String responseText;


        @Override
        protected Object doInBackground(Object[] objects) {
            String path = "http://192.168.56.1:8080/WebServicess/rest/MusicRestService/albumlist";

            try {

                URL url = new URL(path);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(150000);
                conn.setConnectTimeout(150000);
                conn.setRequestMethod("GET");
                int responseCode = conn.getResponseCode();
                String responseMessage = conn.getResponseMessage();


                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
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
                        String ar = jsonobject.getString("artista");
                        String gen = jsonobject.getString("genero");
                        int id = jsonobject.getInt("idAlbum");
////////////////////////////////////////////////////////////////////////////////////////////////////////
                        JSONArray listaCan = jsonobject.getJSONArray("listaCanciones");
                        for (int j = 0; j < listaCan.length(); j++) {
                            JSONObject jsonSongObject = listaCan.getJSONObject(j);

                            String link = jsonSongObject.getString("link");
                            String nomcancion = jsonSongObject.getString("nombreCancion");
                            Cancion obj2 = new Cancion(nomcancion, link);
                            listaCanciones.add(obj2);
                        }
////////////////////////////////////////////////////////////////////////////////////////////////////////
                        String portada = jsonobject.getString("portada");
                        String tit = jsonobject.getString("titulo");

                        Album obj = new Album(id, tit, gen, ar, portada, listaCanciones);

                        lstAlbums.add(obj);

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

            myrv = findViewById(R.id.recyclerview_id_music);
            myAdapter = new RecyclerViewAdapterMusic(getApplicationContext(), lstAlbums);
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
        getMenuInflater().inflate(R.menu.navigation_drawer_music, menu);
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

        if (id == R.id.nav_hip_hop) {
            // Handle the camera action
        } else if (id == R.id.nav_rock) {

        } else if (id == R.id.nav_house) {

        } else if (id == R.id.nav_trap) {

        } else if (id == R.id.nav_heavy_metal) {

        } else if (id == R.id.nav_pop) {

        } else if (id == R.id.nav_jazz) {

        } else if (id == R.id.nav_blues) {

        } else if (id == R.id.nav_hardcore) {

        } else if (id == R.id.nav_all_music) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
