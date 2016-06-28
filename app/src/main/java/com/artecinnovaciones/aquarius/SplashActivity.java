package com.artecinnovaciones.aquarius;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.artecinnovaciones.aquarius.servicioretrofit.Controlador.PecesControlator;
import com.artecinnovaciones.aquarius.servicioretrofit.modelresponse.PecesResponse;

public class SplashActivity extends Activity {

    ProgressBar progress;
    Animation anim;
    ImageView logo, pez, pez_progress;
    TextView porcentaje,descarga;

    LinearLayout Pez_Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        porcentaje = (TextView) findViewById(R.id.porcent);
        descarga = (TextView) findViewById(R.id.descarga);

        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.robotar);
        logo = (ImageView) findViewById(R.id.logo_splash);
        logo.startAnimation(anim);

        progress = (ProgressBar) findViewById(R.id.progressBar2);
        pez_progress = (ImageView) findViewById(R.id.imageViewProgress);
        Pez_Layout = (LinearLayout) findViewById(R.id.LL);

        pez = (ImageView) findViewById(R.id.descargaimg);

        //   animate();
        pez_progress.setBackgroundResource(R.drawable.animacion_pez);
        pez.setBackgroundResource(R.drawable.animacion_pez);
        animar_pez = (AnimationDrawable) pez.getBackground();
        getListPeces();

    }

    public void mover(int mov) {

        Pez_Layout.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(pez_progress.getLayoutParams());
        params.setMargins((int) (mov * 4.3) - pez_progress.getWidth() / 2, 0, 0, 0);
        Pez_Layout.addView(pez_progress, params);

        //porcentaje.setText(mov+" %");

    }

  /*   public void tiempo (){
        animar_pez_progress = (AnimationDrawable) pez_progress.getBackground();
        animar_pez.stop();
        animar_pez_progress.start();
        progress.setVisibility(View.VISIBLE);
        pez_progress.setVisibility(View.VISIBLE);
        Pez_Layout.setVisibility(View.VISIBLE);
        porcentaje.setVisibility(View.VISIBLE);

        pez.setVisibility(View.GONE);
        descarga.setVisibility(View.GONE);

       new Thread(){
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(1000);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int jumpTime = 0;

                                while(jumpTime < 100) {
                                    try {
                                        sleep(80);
                                        jumpTime ++;
                                        progress.setProgress(jumpTime);
                                        mover(jumpTime);
                                        String porcent=""+jumpTime+" %";
                                        porcentaje.setText(porcent);
                                    }
                                    catch (InterruptedException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            };
        }.start();

        new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;

                while(jumpTime < 100) {
                    try {
                        sleep(80);
                        jumpTime += 1;
                        progress.setProgress(jumpTime);
                        mover(jumpTime);

                        //porcentaje.setText(jumpTime+" %");
                    }
                    catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }.start();
    } */

    public void getListPeces() {

        mpecesAsyncTask = new AsyncTask<Void, Integer, PecesResponse>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                if (pez != null && animar_pez != null) {
                    animar_pez.start();
                }
                progress.setVisibility(View.GONE);
                pez_progress.setVisibility(View.GONE);
                Pez_Layout.setVisibility(View.GONE);
                porcentaje.setVisibility(View.GONE);
            }

            @Override
            protected PecesResponse doInBackground(Void... params) {
                PecesResponse mPecesResponse = null;
                try {
                    mPecesResponse = PecesControlator.getInstance(getApplicationContext()).getListPeces();
                    //publishProgress(mPecesResponse.getmListPeces().size());
                    //SystemClock.sleep(5000);
                } catch (Exception e) {
                    e.getMessage();
                }
                return mPecesResponse;
            }


            @Override
            protected void onPostExecute(PecesResponse pecesResponse) {
                //tiempo();
                animar_pez_progress = (AnimationDrawable) pez_progress.getBackground();
                animar_pez.stop();
                animar_pez_progress.start();
                progress.setVisibility(View.VISIBLE);
                pez_progress.setVisibility(View.VISIBLE);
                Pez_Layout.setVisibility(View.VISIBLE);
                porcentaje.setVisibility(View.VISIBLE);

                pez.setVisibility(View.GONE);
                descarga.setVisibility(View.GONE);
                new moverProgress();
            }
        }.execute();

    }

    class moverProgress extends AsyncTask<Void,Integer, Void>{
        int progess=0;

        @Override
        protected Void doInBackground(Void... params) {
            while (progess<100){
                try {
                    progess++;
                    Thread.sleep(80);
                    publishProgress(progess);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            progress.setProgress(values[0]);
            mover(values[0]);
            String porcent=""+values[0]+" %";
            porcentaje.setText(porcent);
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
    }

    private AsyncTask<Void, Integer, PecesResponse> mpecesAsyncTask;
    private AnimationDrawable animar_pez_progress, animar_pez;
}
