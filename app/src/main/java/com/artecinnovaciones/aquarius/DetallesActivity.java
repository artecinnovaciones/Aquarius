package com.artecinnovaciones.aquarius;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.support.v7.widget.SearchView.OnQueryTextListener;

import com.artecinnovaciones.aquarius.fragments.DetallesFragment;
import com.bumptech.glide.Glide;

public class DetallesActivity extends AppCompatActivity implements OnQueryTextListener, OnActionExpandListener {

    private static final String EXTRA_DRAWABLE = "com.artecinnovaciones.artecdemo.drawable";
    String tip="";
    TextView txtB;
    RelativeLayout bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarScroll);
        setSupportActionBar(toolbar);

        tip=getIntent().getStringExtra("tipo");

        txtB = (TextView) findViewById(R.id.text_buscar);
        bus=(RelativeLayout) findViewById(R.id.layout_buscar);

        DetallesFragment DetFrag = new DetallesFragment(tip);
        getFragmentManager().beginTransaction()
                .add(R.id.frag_l, DetFrag).commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 //       .setAction("Action", null).show();
                bus.setVisibility(View.VISIBLE);
                inflater.inflate(R.menu.menu_detalles, menu);

            }
        });

        CollapsingToolbarLayout collapser =
                (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapser.setTitle("");

        if (tip.equals("salada")){
            loadImageParallax(R.drawable.peces_salada);
        }else {
            loadImageParallax(R.drawable.peces_dulce);
        }
    }

    private void loadImageParallax(int id) {
        ImageView image = (ImageView) findViewById(R.id.image_paralax);
        // Usando Glide para la carga asíncrona
        Glide.with(this)
                .load(id)
                .centerCrop()
                .into(image);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       inflater = getMenuInflater();
       this.menu=menu;

        MenuItem searchItem = menu.findItem(R.id.action_search);

        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Buscar");
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(searchItem, this);
      //  inflater.inflate(R.menu.menu_detalles, menu);
/*
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //permite modificar el hint que el EditText muestra por defecto
        //searchView.setQueryHint(getText(R.string.search));
        searchView.setQueryHint("Buscar");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(DetallesActivity.this, "submitted", Toast.LENGTH_SHORT).show();
                //se oculta el EditText
                searchView.setQuery("", false);
                searchView.setIconified(true);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //textView.setText(newText);
                return true;
            }
        }); */


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        Toast.makeText(getApplicationContext(), "EXPAND", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        Toast.makeText(getApplicationContext(), "COLLAPSE", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        txtB.setText("Texto a buscar\n\n" + s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        txtB.setText("Escribiendo texto...\n\n" + s);
        return false;
    }

    private MenuInflater inflater;
    private Menu menu;
}
