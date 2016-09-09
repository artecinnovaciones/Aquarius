package com.artecinnovaciones.aquarius;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.artecinnovaciones.aquarius.adapters.CustomAutoCompleteView;
import com.artecinnovaciones.aquarius.adapters.SearchAdapter;
import com.artecinnovaciones.aquarius.filter.CustomAutoCompleteTextChangedListener;
import com.artecinnovaciones.aquarius.fragments.AgresivosFragment;
import com.artecinnovaciones.aquarius.fragments.TiposFragment;
import com.artecinnovaciones.aquarius.modelodao.ControladorBd.BdController;
import com.artecinnovaciones.aquarius.modelodao.PecesDulce;
import com.artecinnovaciones.aquarius.modelodao.PecesDulceDao;

import java.util.ArrayList;
import java.util.List;

public class DetallesActivity extends AppCompatActivity {

    private static final String EXTRA_DRAWABLE = "com.artecinnovaciones.artecdemo.drawable";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
    //    cargarBd();
        try {
            String info = getIntent().getStringExtra("info");
            String cuidados = getIntent().getStringExtra("cuidados");
            String alimentacion = getIntent().getStringExtra("alimentacion");
            String imagen = getIntent().getStringExtra("img");
            int bandera = getIntent().getIntExtra("bandera",0);
            TiposFragment tiposFragment = new TiposFragment(info, cuidados, alimentacion,bandera);
            getFragmentManager().beginTransaction()
                    .add(R.id.frag_l, tiposFragment).commit();

            Bitmap bMap = BitmapFactory.decodeFile(imagen);

            ImageView image = (ImageView) findViewById(R.id.image_paralax);
            image.setImageBitmap(bMap);
        } catch (Exception e) {
            e.getMessage();
        }
    }
   /* public void cargarBd() {
        try {
            PecesDulceDao mPeces = BdController.getInstance(getApplication()).pecesdulce();
            List listpeces = mPeces.queryBuilder().list();
            ArrayListPeces = new ArrayList<PecesDulce>();
            for (Object peces : listpeces) {
                ArrayListPeces.add((PecesDulce) peces);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }*/

    //public ArrayList<PecesDulce> ArrayListPeces;
}
