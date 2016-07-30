package com.artecinnovaciones.aquarius;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.artecinnovaciones.aquarius.adapters.CustomAutoCompleteView;
import com.artecinnovaciones.aquarius.adapters.SearchAdapter;
import com.artecinnovaciones.aquarius.filter.CustomAutoCompleteTextChangedListener;
import com.artecinnovaciones.aquarius.fragments.DetallesFragment;
import com.artecinnovaciones.aquarius.fragments.PrincipalFragment;
import com.artecinnovaciones.aquarius.fragments.TiposFragment;
import com.artecinnovaciones.aquarius.modelodao.ControladorBd.BdController;
import com.artecinnovaciones.aquarius.modelodao.PecesDulce;
import com.artecinnovaciones.aquarius.modelodao.PecesDulceDao;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class DetallesActivity extends AppCompatActivity {

    private static final String EXTRA_DRAWABLE = "com.artecinnovaciones.artecdemo.drawable";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        cargarBd();

        String info=getIntent().getStringExtra("info");
        String cuidados=getIntent().getStringExtra("cuidados");
        String alimentacion=getIntent().getStringExtra("alimentacion");
        String imagen=getIntent().getStringExtra("img");

        mCustomAutoCompleteView = (CustomAutoCompleteView) findViewById(R.id.autocomplete);
        mCustomAutoCompleteView.setOnItemClickListener(mOnItemClickListener);
        mCustomAutoCompleteView.setVisibility(View.GONE);
        mCustomAutoCompleteView.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));

        mSearchAdapter = new SearchAdapter(this, R.layout.list_view_row, ArrayListPeces);
        mCustomAutoCompleteView.setAdapter(mSearchAdapter);


        /*DetallesFragment DetFrag = new DetallesFragment();
        getFragmentManager().beginTransaction()*/
        TiposFragment tiposFragment = new TiposFragment(info,cuidados,alimentacion);
        getFragmentManager().beginTransaction()
                .add(R.id.frag_l, tiposFragment).commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCustomAutoCompleteView.setVisibility(View.VISIBLE);

            }
        });

        CollapsingToolbarLayout collapser =
                (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapser.setTitle(DetallesFragment.nombre);

        Bitmap bMap = BitmapFactory.decodeFile(imagen);

        ImageView image = (ImageView) findViewById(R.id.image_paralax);
        image.setImageBitmap(bMap);

            //loadImageParallax(d);
    }

    /*private void loadImageParallax(int id) {
        ImageView image = (ImageView) findViewById(R.id.image_paralax);
        // Usando Glide para la carga asíncrona

        Glide.with(this)
                .load(id)
                .centerCrop()
                .into(image);
    } */

    AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            RelativeLayout rl = (RelativeLayout) view;
            TextView tv = (TextView) rl.getChildAt(0);
            mCustomAutoCompleteView.setText(tv.getText().toString());
        }
    };

    public void cargarBd() {
        try {
            PecesDulceDao mPeces = BdController.getInstance(getApplication()).pecesdulce();
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

    public CustomAutoCompleteView mCustomAutoCompleteView;
    public ArrayList<PecesDulce> ArrayListPeces;
    private List<PecesDulce> mListpeces;
    public SearchAdapter mSearchAdapter;
}
