package com.example.f95108_calisthenics_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ChooserActivity extends AppCompatActivity {

    private final String URL = "https://api.api-ninjas.com/v1/caloriesburned?activity=";
    private final String API_KEY="evYS5tC+eVVak1jfEnfiVQ==98PEiEXYirp1FYwB";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);


    }
}