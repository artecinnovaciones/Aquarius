package com.artecinnovaciones.aquarius;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.artecinnovaciones.aquarius.adapters.DetallesAdapter;
import com.artecinnovaciones.aquarius.adapters.SearchAdapter;
import com.artecinnovaciones.aquarius.fragments.DetallesFragment;
import com.artecinnovaciones.aquarius.modelodao.ControladorBd.BdController;
import com.artecinnovaciones.aquarius.modelodao.PecesDulce;
import com.artecinnovaciones.aquarius.modelodao.PecesDulceDao;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class DetallesActivity extends AppCompatActivity {

    private static final String EXTRA_DRAWABLE = "com.artecinnovaciones.artecdemo.drawable";
    String tip = "";
    AutoCompleteTextView autocomplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        cargarBd();


        autocomplete = (AutoCompleteTextView) findViewById(R.id.toolbarSearch);
        autocomplete.setVisibility(View.VISIBLE);

        adapter = new SearchAdapter(getBaseContext(), mListpeces);

        autocomplete.setThreshold(2);
        autocomplete.setAdapter(adapter);


        tip = getIntent().getStringExtra("tipo");

        DetallesFragment DetFrag = new DetallesFragment(tip);
        getFragmentManager().beginTransaction()
                .add(R.id.frag_l, DetFrag).commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        CollapsingToolbarLayout collapser =
                (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapser.setTitle("");

        if (tip.equals("salada")) {
            loadImageParallax(R.drawable.peces_salada);
        } else {
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

    public void cargarBd() {
        if (ArrayListPeces == null) {
            try {
                PecesDulceDao mPeces = BdController.getInstance(getBaseContext()).pecesdulce();
                List listpeces = mPeces.queryBuilder().list();
                ArrayListPeces = new ArrayList<PecesDulce>();
                for (Object peces : listpeces) {
                    ArrayListPeces.add((PecesDulce) peces);
                }
                mListpeces = ArrayListPeces;
            } catch (Exception e) {
                e.getStackTrace();
            }
        }

    }

    private ArrayList<PecesDulce> ArrayListPeces;
    private List<PecesDulce> mListpeces;
    private SearchAdapter adapter;
}
