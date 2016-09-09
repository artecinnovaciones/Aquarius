package com.artecinnovaciones.aquarius;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import com.artecinnovaciones.aquarius.fragments.TiposFragment;

public class DetallesActivity extends AppCompatActivity {

    private static final String EXTRA_DRAWABLE = "com.artecinnovaciones.artecdemo.drawable";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
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
}
