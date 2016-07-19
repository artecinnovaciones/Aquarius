package com.artecinnovaciones.aquarius;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.artecinnovaciones.aquarius.servicioretrofit.Controlador.PecesControlator;
import com.artecinnovaciones.aquarius.servicioretrofit.modelresponse.PecesResponse;

public class MainActivity extends AppCompatActivity {

    public static String tipo_pez;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout dul = (LinearLayout) findViewById(R.id.peces_dul);
        dul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipo_pez="dulce";
                Intent i = new Intent(MainActivity.this, DetallesActivity.class);
                startActivity(i);
            }
        });

        LinearLayout enfermedades = (LinearLayout) findViewById(R.id.enfermedades_btn);
        enfermedades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipo_pez="enfermedades";
                Intent i = new Intent(MainActivity.this, DetallesActivity.class);
                startActivity(i);
            }
        });
    }


}
