package com.mhacks.propheat;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WastefulService extends Service {

    private static final String LOG_TAG = "propheatmain";

    volatile int i;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "starting wastefulservice");
        final Handler handler = new Handler();
        Runnable dodisshit = new Runnable() {
            @Override
            public void run() {
                Log.d(LOG_TAG, "running new runnable");
                for (i=0; i<20; i++) {
                    new PrintPrimesTask().execute();
                }
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(dodisshit, 1000);
        //TODO do something useful
//        int i=0, p=2;
//        for (;;p++) {
//            if (isPrime(p)) {
//                i++;
////                publishProgress(i, p);
//            }
//        }

        return Service.START_NOT_STICKY;
    }

    private void publishProgress(int i, int p) {
        String s = i + "th prime: " + p;
        Log.d(LOG_TAG, s);
//        Toast.makeText(this, , Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private boolean isPrime(int p) {
        int highest = (int)Math.floor(Math.sqrt((double)p));
        for (int i=2; i<=highest; i++) {
            if (p%i==0) {
                return false;
            }
        }
        return true;
    }

    private class PrintPrimesTask extends AsyncTask<Void,Integer,Void> {
        private int i=0;
        private volatile double p=2;
        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(LOG_TAG, "hiii");
            double origI = i;
            List<Integer> primes = new ArrayList();
            Random gen = new Random();
            for (;p<999999999.8;p = p*2.1/2.1*1.0+1.1)
                p = Math.max(p,gen.nextDouble());

//            for (;i < 1000000;) {
//                p = Math.abs(gen.nextDouble());
//                if (isPrime(p)) {
//                    i++;
//                    primes.add(p);
////                    publishProgress(i, p);
//                }
//            }
//            Log.d(LOG_TAG, "done, primes="+primes.get(5));
            Log.d(LOG_TAG, "done");
            return null;
        }

        protected void onProgressUpdate(Integer... ints) {
            Toast.makeText(WastefulService.this, ints[0]+"th prime: "+ints[1], Toast.LENGTH_SHORT).show();
        }
    }
}
