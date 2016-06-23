package com.artecinnovaciones.aquarius;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ProgressBar;

public class SplashActivity extends Activity {

    ProgressBar h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        h=(ProgressBar)findViewById(R.id.progressBar);
        h.setProgress(0);

        new AsyncTask_load().execute();
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
                SystemClock.sleep(30);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            h.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
    }
}
