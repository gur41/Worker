package com.example.hurski_r.worker;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    public  Integer start;
    public  Integer end;
    public  Integer timing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        askPermission();
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //getOptions();
        Intent intent = getIntent();
        Integer id = intent.getIntExtra("id", 0);
        Integer start = intent.getIntExtra("start", 0);
        Integer end = intent.getIntExtra("end", 0);
        Integer timing = intent.getIntExtra("timing", 0);
        Log.d("workmng", "doWork: start1");
        WorkManager.getInstance().cancelAllWorkByTag("Cleaning");
        for(int i =0; i<Math.abs(end-start)/timing;i++) {
            Data myData = new Data.Builder()
                    .putInt("id", id)
                    .putInt("start",start)
                    .putInt("end",end)
                    .build();
            OneTimeWorkRequest myWorkRequest = new OneTimeWorkRequest.Builder(MyWorker.class).setInputData(myData)
                    .setInitialDelay(timing*60*i, TimeUnit.SECONDS).addTag("Cleaning").build();
            WorkManager.getInstance().enqueue(myWorkRequest);
        }
        Intent intentWorker = new Intent(MainActivity.this, OrderActivity.class);
        startActivity(intentWorker);
    }

    public void askPermission(){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    1);        }
        ;
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);
        }
        ;
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECEIVE_BOOT_COMPLETED)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.RECEIVE_BOOT_COMPLETED},
                    1);
        }
        ;
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.INTERNET},
                    1);
        }
        ;
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_NETWORK_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.ACCESS_NETWORK_STATE},
                    1);
        }
    }



}
