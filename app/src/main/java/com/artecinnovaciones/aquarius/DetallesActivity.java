package com.artecinnovaciones.aquarius;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.artecinnovaciones.aquarius.fragments.DetallesFragment;
import com.bumptech.glide.Glide;

public class DetallesActivity extends AppCompatActivity {

    private static final String EXTRA_DRAWABLE = "com.artecinnovaciones.artecdemo.drawable";
    String tip="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarScroll);
        setSupportActionBar(toolbar);

        tip=getIntent().getStringExtra("tipo");

        DetallesFragment DetFrag = new DetallesFragment(tip);
        getFragmentManager().beginTransaction()
                .add(R.id.frag_l, DetFrag).commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 //       .setAction("Action", null).show();
                if (menu != null) menu.clear();
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

        searchItem = menu.findItem(R.id.action_search);
        //searchItem.setIcon(R.drawable.buscar);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private MenuInflater inflater;
    private Menu menu;
    MenuItem searchItem;
}
