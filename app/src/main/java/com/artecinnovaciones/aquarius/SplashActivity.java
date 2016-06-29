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
    TextView porcentaje, descarga;

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

        pez_progress.setBackgroundResource(R.drawable.animacion_pez);
        pez.setBackgroundResource(R.drawable.animacion_pez);

        animar_pez = (AnimationDrawable) pez.getBackground();
        animar_pez_progress = (AnimationDrawable) pez_progress.getBackground();
        getListPeces();

    }

    public void mover(int mov) {

        Pez_Layout.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(pez_progress.getLayoutParams());
        params.setMargins((int) (mov * 4.3) - pez_progress.getWidth() / 2, 0, 0, 0);
        Pez_Layout.addView(pez_progress, params);

    }

    public void getListPeces() {

        mpecesAsyncTask = new AsyncTask<Void, Integer, PecesResponse>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                if (pez != null && animar_pez != null) {
                    animar_pez.start();
                }
            }

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
            protected void onPostExecute(PecesResponse pecesResponse) {
                animar_pez.stop();
                getVisivility();
                animar_pez_progress.start();
                moverProgress();
            }
        }.execute();

    }

    private void getVisivility() {
        pez.setVisibility(View.GONE);
        descarga.setVisibility(View.GONE);
        pez_progress.setVisibility(View.VISIBLE);
        Pez_Layout.setVisibility(View.VISIBLE);
        porcentaje.setVisibility(View.VISIBLE);
        progress.setVisibility(View.VISIBLE);

    }


    private void moverProgress() {
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


    }

    private AsyncTask<Void, Integer, PecesResponse> mpecesAsyncTask;
    private AsyncTask<Void, Integer, Void> mMoverPezAsyncTask;
    private AnimationDrawable animar_pez_progress, animar_pez;
}
