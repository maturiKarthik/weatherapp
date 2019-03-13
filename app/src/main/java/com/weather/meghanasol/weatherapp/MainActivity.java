package com.weather.meghanasol.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView tv_weatherdata;
    private String[] climate = {"Rainy","Sunny","Snowy","Cloudy","Fog","Strom","HailStrom","Hot","Rain With Ice","Pitch Dark","If You Have Something Suggest Us"} ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_weatherdata = (TextView) findViewById(R.id.tv_weatherdata);

        for (String weather: climate) {
            tv_weatherdata.append(""+weather+"\n\n\n");
        }

    }
}
