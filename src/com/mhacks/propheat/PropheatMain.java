package com.mhacks.propheat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
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
                //for (int i=0; i<10; i++) new GetLocationTask().execute();
//                for (int i=0; i<10; i++) doitt();
                for (int i=0; i<20; i++) {
                    Intent service = new Intent(PropheatMain.this, WastefulService.class);
                    PropheatMain.this.startService(service);
                }
                //for (int o=0; o<9999; o++) new PrintPrimesTask().execute();
            }
        });
        layout.addView(start);
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = pm.newWakeLock(
                PowerManager.FULL_WAKE_LOCK, "My wakelook");
// This will make the screen and power stay on
        wakeLock.acquire();
        doitt();

// Alternative you can request and / or  release the wakelook via:
// wakeLock.acquire(); wakeLock.release();
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
            Log.d(LOG_TAG, "hiii");
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

    private class GetLocationTask extends AsyncTask<Void,Integer,Void> {
        private LocationManager locMgr=null;
        @Override
        protected Void doInBackground(Void... voids) {
            locMgr=(LocationManager)getSystemService(LOCATION_SERVICE);
            locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    0, 0,
                    new LocationListener() {
                        public void onLocationChanged(Location fix) {
//                            helper.updateLocation(restaurantId, fix.getLatitude(),
//                                    fix.getLongitude());
//                            location.setText(String.valueOf(fix.getLatitude())
//                                    +", "
//                                    +String.valueOf(fix.getLongitude()));
                            locMgr.removeUpdates(this);
                            Log.d(LOG_TAG, String.valueOf(fix.getLatitude())+", "+String.valueOf(fix.getLongitude()));

//                            Toast
//                                    .makeText(DetailForm.this, "Location saved",
//                                            Toast.LENGTH_LONG)
//                                    .show();
                        }

                        public void onProviderDisabled(String provider) {
                            // required for interface, not used
                        }

                        public void onProviderEnabled(String provider) {
                            // required for interface, not used
                        }

                        public void onStatusChanged(String provider, int status,
                                                    Bundle extras) {
                            // required for interface, not used
                        }
                    });
            return null;
        }

        protected void onProgressUpdate(Integer... ints) {
        }
    }

    void doitt() {
        final LocationManager locMgr=(LocationManager)getSystemService(LOCATION_SERVICE);
        locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                0, 0,
                new LocationListener() {
                    public void onLocationChanged(Location fix) {
//                            helper.updateLocation(restaurantId, fix.getLatitude(),
//                                    fix.getLongitude());
//                            location.setText(String.valueOf(fix.getLatitude())
//                                    +", "
//                                    +String.valueOf(fix.getLongitude()));
//                        locMgr.removeUpdates(this);
                        Log.d(LOG_TAG, String.valueOf(fix.getLatitude())+", "+String.valueOf(fix.getLongitude()));

//                            Toast
//                                    .makeText(DetailForm.this, "Location saved",
//                                            Toast.LENGTH_LONG)
//                                    .show();
                    }

                    public void onProviderDisabled(String provider) {
                        // required for interface, not used
                    }

                    public void onProviderEnabled(String provider) {
                        // required for interface, not used
                    }

                    public void onStatusChanged(String provider, int status,
                                                Bundle extras) {
                        // required for interface, not used
                    }
                });
    }
}
