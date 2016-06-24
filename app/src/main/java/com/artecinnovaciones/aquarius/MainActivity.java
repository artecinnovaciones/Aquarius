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

    boolean click = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        LinearLayout sal = (LinearLayout) findViewById(R.id.peces_sal);
        sal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DetallesActivity.class);
                i.putExtra("tipo", "salada");
                startActivity(i);
            }
        });

        LinearLayout dul = (LinearLayout) findViewById(R.id.peces_dul);
        dul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getListPeces();
            /*    Intent i = new Intent(MainActivity.this, DetallesActivity.class);
                i.putExtra("tipo", "dulce");
                startActivity(i);*/
            }
        });
    }


    public void getListPeces() {
        mpecesAsyncTask = new AsyncTask<Void, Void, PecesResponse>() {
            @Override
            protected PecesResponse doInBackground(Void... params) {
                PecesResponse s = null;
                try {
                     s = PecesControlator.getInstance(getApplicationContext()).getListPeces();

                } catch (Exception e) {
                    e.getMessage();
                }
                return s;
            }
        }.execute();

    }


    private AsyncTask<Void, Void, PecesResponse> mpecesAsyncTask;


}
