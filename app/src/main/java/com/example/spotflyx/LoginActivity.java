package com.example.spotflyx;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class LoginActivity extends AppCompatActivity {
    public static ArrayList<Usuario> lstUsers = new ArrayList<Usuario>();
    public boolean situacion = false;
    public boolean encontrado = false;
    String ruta;

    private ImageView polilla;
    private ImageView spotflix;
    private Button boton;
    private ImageView imgloguin;
    private ImageView imgcontraseña;
    private TextInputEditText nombre;
    private TextInputEditText contraseña;
    private TextInputLayout nombreA;
    private TextInputLayout contraseñaB;
    private TextView nuevousuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        boolean crear = false;
        polilla = findViewById(R.id.polilla);
        spotflix = findViewById(R.id.spotflix);
        boton = findViewById(R.id.boton);
        nombre = findViewById(R.id.inputnombre);
        contraseña = findViewById(R.id.inputcontraseña);
        nuevousuario = findViewById(R.id.nuevousuario);
        nombreA = findViewById(R.id.textInputLayoutA);
        contraseñaB = findViewById(R.id.textInputLayoutB);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.transparencia);
        polilla.startAnimation(animation);
        spotflix.startAnimation(animation);
        boton.startAnimation(animation);
        nuevousuario.setVisibility(View.INVISIBLE);
        nombre.startAnimation(animation);
        contraseña.startAnimation(animation);
        nombreA.setCounterEnabled(true);
        nombreA.setCounterMaxLength(10);

        new DatosServidorUser().execute();
    }


    public void entrar(View v) {

        if (situacion == false) {

            String nombrestring = String.valueOf(nombre.getText());
            String contraseñastring = String.valueOf(contraseña.getText());

            for (int i = 0; i < lstUsers.size(); i++) {
                Usuario usuariolst = lstUsers.get(i);

                if (nombrestring.equalsIgnoreCase(usuariolst.usuario) && contraseñastring.equalsIgnoreCase(usuariolst.contraseña)) {
                    encontrado = true;
                    Intent intent = new Intent(this, OptionsActivity.class);
                    startActivity(intent);
                }
            }

            if (encontrado == false) {
                crearUsuario();
            }
        }


        if (situacion == true) {
            String nombrestring = String.valueOf(nombre.getText());
            String contraseñastring = String.valueOf(contraseña.getText());
            boolean existe = false;
            for (int i = 0; i < lstUsers.size(); i++) {
                Usuario usuariolst = lstUsers.get(i);

                if (nombrestring == usuariolst.usuario) {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Lo siento el usuario ya existe", Toast.LENGTH_SHORT);
                    toast1.show();
                    nombre.setText("");
                    contraseña.setText("");
                    existe = true;
                }
            }
            if (existe == false) {
                ruta = "http://192.168.56.1:8080/WebServicess/rest/UsersRestService/" + nombrestring + "/" + contraseñastring;
                new EscrituraServidorUser().execute();
                Toast toast1 = Toast.makeText(getApplicationContext(), "Usuario creado satisfactoriamente", Toast.LENGTH_SHORT);
                toast1.show();
                nuevousuario.setVisibility(View.INVISIBLE);
                situacion = false;
                encontrado = false;
                Intent intent = new Intent(getApplicationContext(), OptionsActivity.class);
                startActivity(intent);
            }
        }
    }

    public void crearUsuario() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("El usuario no existe ¿Quieres crear un nuevo usuario?");
        builder.setMessage("");

        AlertDialog.Builder yes = builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                nuevousuario.setVisibility(View.VISIBLE);
                nombre.setText("");
                contraseña.setText("");
                boton.setText("Crear");
                situacion = true;
                dialog.dismiss();

            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nombre.setText("");
                contraseña.setText("");
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    class DatosServidorUser extends AsyncTask {

        String responseText;

        @Override
        protected Object doInBackground(Object[] objects) {
            String path = "http://192.168.56.1:8080/WebServicess/rest/UsersRestService/usuarios";

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
                        /*  et.setText(jsonobject.getString("Titulo"));*/
                        String nombre = jsonobject.getString("usuario");
                        String contrasena = jsonobject.getString("contrasena");
                        Usuario obj = new Usuario(nombre, contrasena);

                        lstUsers.add(obj);
                    }
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    class EscrituraServidorUser extends AsyncTask {

        String responseText;

        @Override
        protected Object doInBackground(Object... objects) {

            URL url = null;
            try {
                url = new URL(ruta);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(150000);
                conn.setConnectTimeout(150000);
                conn.setRequestMethod("GET");
                int responseCode = conn.getResponseCode();
                String responseMessage = conn.getResponseMessage();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Usuario creado satisfactoriamente", Toast.LENGTH_SHORT);
                    toast1.show();
                }

                return null;
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}