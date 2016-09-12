package com.artecinnovaciones.aquarius;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.artecinnovaciones.aquarius.fragments.IdeasFragment;

public class PecerasDetallesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peceras_detalles);

        ImageView image = (ImageView) findViewById(R.id.image_peceras);

        if(PecerasActivity.Peceras==0){
            image.setImageResource(R.drawable.peces_salada);
            IdeasFragment ideasFRag = new IdeasFragment();
            getFragmentManager().beginTransaction()
                    .add(R.id.frag_Peceras,ideasFRag).commit();
        }else{
            image.setImageResource(R.drawable.peces_peceras);
        }

    }
}
