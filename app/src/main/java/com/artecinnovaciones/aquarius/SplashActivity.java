package com.artecinnovaciones.aquarius;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.artecinnovaciones.aquarius.servicioretrofit.Controlador.PecesControlator;
import com.artecinnovaciones.aquarius.servicioretrofit.modelresponse.PecesResponse;

public class SplashActivity extends Activity {

    ProgressBar h;
    Animation anim;
    ImageView logo,pez;
    TextView p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        p=(TextView)findViewById(R.id.porcent);
        pez=(ImageView)findViewById(R.id.pez);

        h=(ProgressBar)findViewById(R.id.progressBar);
        //h.setVisibility(View.GONE);
        h.setProgress(0);

        anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.robotar);
        logo = (ImageView)findViewById(R.id.logo_splash);
        logo.startAnimation(anim);

        animate();
        getListPeces();

        //new AsyncTask_load().execute();
    }

    private void animate(){
        pez.setBackgroundResource(R.drawable.animacion_pez);
        AnimationDrawable frame = (AnimationDrawable) pez.getBackground();
            frame.stop();
            frame.start();
    }
/*
    public class AsyncTask_load extends AsyncTask<Void,Integer,Void>{
        int progreso;

        @Override
        protected void onPreExecute() {
            progreso= 0;
        }

        @Override
        protected Void doInBackground(Void... params) {
            while (progreso < 100){
                progreso++;
                publishProgress(progreso);
                SystemClock.sleep(80);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            h.setProgress(values[0]);
            p.setText(values[0]+" %");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
    } */

    public void getListPeces() {
        mpecesAsyncTask = new AsyncTask<Void, Integer, PecesResponse>() {
            @Override
            protected PecesResponse doInBackground(Void... params) {
                PecesResponse mPecesResponse = null;
                try {
                    mPecesResponse = PecesControlator.getInstance(getApplicationContext()).getListPeces();

                } catch (Exception e) {
                    e.getMessage();
                }
                return mPecesResponse;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                h.setProgress(values[0]);
                p.setText(values[0] + " %");
            }

            @Override
            protected void onPostExecute(PecesResponse pecesResponse) {
                if(pecesResponse!=null ){

                }
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }.execute();

    }


    private AsyncTask<Void, Integer, PecesResponse> mpecesAsyncTask;
}
