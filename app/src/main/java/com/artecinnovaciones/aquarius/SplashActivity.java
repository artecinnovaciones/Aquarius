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
        h.setVisibility(View.GONE);
        h.setProgress(0);

        anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.robotar);
        logo = (ImageView)findViewById(R.id.logo_splash);
        logo.startAnimation(anim);

        animate();

        new AsyncTask_load().execute();
    }

    private void animate(){
        pez.setBackgroundResource(R.drawable.animacion_pez);

        AnimationDrawable frame = (AnimationDrawable) pez.getBackground();
        if(frame.isRunning()){
            frame.stop();
        }else{
            frame.stop();
            frame.start();
        }
    }

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
    }
}
