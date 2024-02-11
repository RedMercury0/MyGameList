package com.nirtzmeret.dorontayar.gamewiki;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import java.net.HttpURLConnection;

import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}