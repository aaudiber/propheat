package com.mhacks.propheat;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class PropheatMain extends Activity {
    private static final String LOG_TAG = "propheatmain";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        LinearLayout layout = new LinearLayout(this);
        Button start = new Button(this);
        start.setText("start");
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PrintPrimesTask().execute();
            }
        });
        layout.addView(start);
        setContentView(layout);
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
        private int p=2;
        @Override
        protected Void doInBackground(Void... voids) {
            int origI = i;
            for (;p<50;p++) {
                if (isPrime(p)) {
                    i++;
                    publishProgress(i, p);
                }
            }
            return null;
        }

        protected void onProgressUpdate(Integer... ints) {
            Toast.makeText(PropheatMain.this, ints[0]+"th prime: "+ints[1], Toast.LENGTH_SHORT).show();
        }
    }
}
