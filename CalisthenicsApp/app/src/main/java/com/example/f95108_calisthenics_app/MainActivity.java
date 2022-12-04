package com.example.f95108_calisthenics_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {


    private Button bodyBtn;
    private Button activityBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bodyBtn = findViewById(R.id.btnBody);
        activityBtn = findViewById(R.id.btnActivity);
//        bodyBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                fragmentManager.beginTransaction();
//            }
//        });

    }
}