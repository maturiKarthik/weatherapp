package com.weather.meghanasol.weatherapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity {


    TextView tv_weatherdata;
    private String[] climate = {"Rainy","Sunny","Snowy","Cloudy","Fog","Strom","HailStrom","Hot","Rain With Ice","Pitch Dark","If You Have Something Suggest Us"} ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_weatherdata = (TextView) findViewById(R.id.tv_weatherdata);

        Intent intent = getIntent();
        String dat = intent.getStringExtra("key");

        tv_weatherdata.setText(dat);
        tv_weatherdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_VIEW,Uri.parse("https://developer.android.com/guide/components/intents-common#Browser"));
                startActivity(intent1);
            }
        });



    }





}
