package com.artecinnovaciones.aquarius;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class PecerasActivity extends AppCompatActivity {

    CardView ideas, decorar, galeria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_peceras);
        }catch(Exception e){
            e.getMessage();
        }

        ideas= (CardView)findViewById(R.id.card_ideas);
        decorar=(CardView)findViewById(R.id.card_decorar);
        galeria=(CardView)findViewById(R.id.card_galeria);

        ideas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PecerasActivity.this, PecerasDetallesActivity.class);
                i.putExtra("tiposP", 0);
                startActivity(i);
            }
        });

        decorar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PecerasActivity.this, PecerasDetallesActivity.class);
                i.putExtra("tiposP", 1);
                startActivity(i);
            }
        });

        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PecerasActivity.this, GaleriaActivity.class));
            }
        });
    }
}
