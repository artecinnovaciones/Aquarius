package com.artecinnovaciones.aquarius;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class PecerasActivity extends AppCompatActivity {

    CardView ideas, decorar, galeria;
    public static int Peceras = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peceras);

        ideas= (CardView)findViewById(R.id.card_ideas);
        decorar=(CardView)findViewById(R.id.card_decorar);
        galeria=(CardView)findViewById(R.id.card_galeria);

        ideas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PecerasActivity.this, PecerasDetallesActivity.class));
            }
        });

        decorar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Peceras=1;
                startActivity(new Intent(PecerasActivity.this, PecerasDetallesActivity.class));
            }
        });

        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PecerasActivity.this,GaleriaActivity.class));
            }
        });
    }
}
