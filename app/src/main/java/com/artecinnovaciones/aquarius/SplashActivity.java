package com.artecinnovaciones.aquarius;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
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
    ImageView logo, pez2;
    TextView porcentaje;

    LinearLayout LL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        porcentaje = (TextView) findViewById(R.id.porcent);

        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.robotar);
        logo = (ImageView) findViewById(R.id.logo_splash);
        logo.startAnimation(anim);

        progress = (ProgressBar) findViewById(R.id.progressBar2);
        pez2 = (ImageView) findViewById(R.id.imageViewProgress);
        LL = (LinearLayout) findViewById(R.id.LL);

        //   animate();
        pez2.setBackgroundResource(R.drawable.animacion_pez);
        frame = (AnimationDrawable) pez2.getBackground();
        getListPeces();

        // new AsyncTask_load().execute();
    }

  /*  private void animate(){
        pez2.setBackgroundResource(R.drawable.animacion_pez);
        AnimationDrawable frame = (AnimationDrawable)
                pez2.getBackground();
            frame.stop();
            frame.start();
    }*/

    public void mover(int mov) {
        LL.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(pez2.getLayoutParams());
        params.setMargins((int) (mov * 4.3) - pez2.getWidth() / 2, 0, 0, 0);
        LL.addView(pez2, params);
    }

  /*  public class AsyncTask_load extends AsyncTask<Void,Integer,Void>{
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
            progress.setProgress(values[0]);
            mover(values[0]);
            porcentaje.setText(values[0] + " %");
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
            protected void onPreExecute() {
                super.onPreExecute();
                if (pez2 != null && frame != null) {
                    frame.start();
                }
            }


            @Override
            protected PecesResponse doInBackground(Void... params) {
                PecesResponse mPecesResponse = null;
                try {

                    mPecesResponse = PecesControlator.getInstance(getApplicationContext()).getListPeces();
                    publishProgress(mPecesResponse.getmListPeces().size());
                    SystemClock.sleep(5000);
                } catch (Exception e) {
                    e.getMessage();
                }
                return mPecesResponse;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                for (int i = 0; i < values.length; i++) {
                    progress.setProgress(values[0]);
                    mover(values[0]);
                    porcentaje.setText(values[0] + " %");
                }
            }

            @Override
            protected void onPostExecute(PecesResponse pecesResponse) {
                if (frame != null) {
                    frame.stop();
                }
                if (pecesResponse != null) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }

            }
        }.execute();

    }


    private AsyncTask<Void, Integer, PecesResponse> mpecesAsyncTask;
    private AnimationDrawable frame;
}
