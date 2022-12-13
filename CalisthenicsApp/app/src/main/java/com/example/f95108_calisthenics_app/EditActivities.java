package com.example.f95108_calisthenics_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EditActivities extends AppCompatActivity {

    private Button btnAccept;
    private Button btnDecline;
    private DatabaseHelper dbController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_activities);

        btnAccept = findViewById(R.id.acceptBtn);
        btnDecline = findViewById(R.id.declineBtn);
        dbController = new DatabaseHelper(this);

        TextView activityName = findViewById(R.id.activityName);
        TextView activityCalories = findViewById(R.id.activityCalories);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("id") && getIntent().getStringExtra("id") == "1") {
                if (extras.containsKey("name")) {
                    activityName.setText(getIntent().getStringExtra("name"));
                }
                if (extras.containsKey("calories")) {
                    activityCalories.setText(getIntent().getStringExtra("calories"));
                }

                btnAccept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String date = dbController.getDateInSuitableFormat();
                        Integer id = dbController.findActivityIdToInsert(date);

                    }
                });
            }
        }

    }

    private void DetermineFunctionality(String id){
        switch (id){
            case "1":
                insertFunctionality();

        }
    }

    private void insertFunctionality(){

    }

    private void editFunctionality(){

    }
}