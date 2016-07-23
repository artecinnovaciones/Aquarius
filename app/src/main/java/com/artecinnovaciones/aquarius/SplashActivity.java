package com.artecinnovaciones.aquarius;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.artecinnovaciones.aquarius.servicioretrofit.Controlador.PecesControlator;
import com.artecinnovaciones.aquarius.servicioretrofit.modelresponse.PecesResponse;
import com.artecinnovaciones.aquarius.sharedpreferenceutils.SharedUtils;

public class SplashActivity extends Activity {

    ProgressBar progress;
    Animation anim;
    ImageView logo, pez_progress;
    TextView porcentaje;

    int ancho = 0;

    LinearLayout Pez_Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        porcentaje = (TextView) findViewById(R.id.porcent);

        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.robotar);
        logo = (ImageView) findViewById(R.id.logo_splash);
        logo.startAnimation(anim);

        progress = (ProgressBar) findViewById(R.id.progressBar);
        pez_progress = (ImageView) findViewById(R.id.imageViewProgress);
        Pez_Layout = (LinearLayout) findViewById(R.id.LL);

        pez_progress.setBackgroundResource(R.drawable.animacion_pez);

        animar_pez_progress = (AnimationDrawable) pez_progress.getBackground();
        getListPeces();

    }

    public void mover(int mov) {

        ancho = (progress.getWidth()) / 100;

        Pez_Layout.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(pez_progress.getLayoutParams());
        params.setMargins((int) (mov * ancho), 0, 0, 0);
        Pez_Layout.addView(pez_progress, params);

    }

    public void getListPeces() {

        mpecesAsyncTask = new AsyncTask<Void, Integer, PecesResponse>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                if (pez_progress != null && animar_pez_progress != null) {
                    animar_pez_progress.start();
                }
                //   mProgressDialog.show();
            }

            @Override
            protected PecesResponse doInBackground(Void... params) {
                int banderaparawebservie = 0;
                PecesResponse mPecesResponse = null;
                int i=0;
                try {
                    while (true) {
                        if (mPecesResponse == null && SharedUtils.getInstance(getApplicationContext()).getIfDowload() == 0 &&SharedUtils.getInstance(getApplicationContext()).getIfDowloadEnfermedades() == 0) {
                            if (banderaparawebservie < 1) {
                                mPecesResponse = PecesControlator.getInstance(getApplicationContext()).getListPeces(getApplicationContext());
                                banderaparawebservie = 1;
                            }
                            i++;

                            if (i>=40){
                                SystemClock.sleep(200);
                                if (i>=60){
                                    SystemClock.sleep(300);
                                    if (i>=75){
                                        SystemClock.sleep(400);
                                    }
                                }
                            }else {
                                SystemClock.sleep(100);
                            }

                            publishProgress(i);
                        }else{
                            while (i < 100) {
                                i++;
                                SystemClock.sleep(80);
                                publishProgress(i);
                            }
                            break;
                        }
                    }
                   // publishProgress(100);

                } catch (Exception e) {
                    e.getMessage();
                }
                return mPecesResponse;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                progress.setProgress(values[0]);
                mover(values[0]);
                String porcent = "" + values[0] + " %";
                porcentaje.setText(porcent);
            }

            @Override
            protected void onPostExecute(PecesResponse pecesResponse) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                SharedUtils.getInstance(getBaseContext()).getclear();
                finish();
            }
        }.execute();

    }

   /* private void moverProgress() {
        mMoverPezAsyncTask = new AsyncTask<Void, Integer, Void>() {
            int progess = 0;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {
                while (progess < 100) {
                    progess++;
                    SystemClock.sleep(100);
                    publishProgress(progess);
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {

                progress.setProgress(values[0]);
                mover(values[0]);
                String porcent = "" + values[0] + " %";
                porcentaje.setText(porcent);
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }.execute();

    }*/

    private AsyncTask<Void, Integer, PecesResponse> mpecesAsyncTask;
    private AsyncTask<Void, Integer, Void> mMoverPezAsyncTask;
    private AnimationDrawable animar_pez_progress;

}
