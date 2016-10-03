package com.artecinnovaciones.aquarius;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.artecinnovaciones.aquarius.sharedpreferenceutils.SharedUtils;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class PecerasActivity extends AppCompatActivity {

    CardView ideas, decorar, galeria;
    private InterstitialAd mInterstitialAd;

    private static int clic=0;

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

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        requestNewInterstitial();

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                if (clic==0){
                    Intent i = new Intent(PecerasActivity.this, PecerasDetallesActivity.class);
                    i.putExtra("tiposP", 0);
                    startActivity(i);
                }
                if (clic==1){
                    Intent i = new Intent(PecerasActivity.this, PecerasDetallesActivity.class);
                    i.putExtra("tiposP", 1);
                    startActivity(i);
                }
                if (clic==2){
                    startActivity(new Intent(PecerasActivity.this, GaleriaActivity.class));
                }
            }
        });

        ideas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInterstitial();
            }
        });

        decorar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clic=1;
                showInterstitial();
            }
        });

        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clic=2;
                showInterstitial();
            }
        });
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    private void showInterstitial() {
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            if (clic==0){
                Intent i = new Intent(PecerasActivity.this, PecerasDetallesActivity.class);
                i.putExtra("tiposP", 0);
                startActivity(i);
            }
            if (clic==1){
                Intent i = new Intent(PecerasActivity.this, PecerasDetallesActivity.class);
                i.putExtra("tiposP", 1);
                startActivity(i);
            }
            if (clic==2){
                startActivity(new Intent(PecerasActivity.this, GaleriaActivity.class));
            }
        }
    }
}
