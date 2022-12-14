package com.example.f95108_calisthenics_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Date;

public class EditActivities extends AppCompatActivity {

    private Button btnAccept;
    private Button btnDecline;
    private DatabaseHelper dbController;
    private Integer calories;
    private Integer duration;
    private TextView minutes;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_activities);

        btnAccept = findViewById(R.id.acceptBtn);
        btnDecline = findViewById(R.id.declineBtn);
        dbController = new DatabaseHelper(this);

        TextView error = findViewById(R.id.activityName);
        minutes = findViewById(R.id.editActivityMinutes);
        txt = findViewById(R.id.activityName);

        btnDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("id") && (getIntent().getStringExtra("id").equals("1"))) {
                if (extras.containsKey("name") && extras.containsKey("calories")) {
                    btnAccept.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String date = dbController.getDateInSuitableFormat(new Date());
                            Integer id = dbController.findActivityIdToInsert(date);
                            String activityName = getIntent().getStringExtra("name");
                            try {
                                calculateDurationAndCalories();
                                dbController.insertOrUpdateActivityTable(date, id, activityName, duration, calories);
                                finish();
                            } catch (Exception e) {
                                error.setText(e.getMessage());
                                e.printStackTrace();
                            }

                        }
                    });
                }
            } else if (extras.containsKey("id") && (getIntent().getStringExtra("id").equals("2"))) {
                if (
                        extras.containsKey("date") &&
                                extras.containsKey("activity_id") &&
                                extras.containsKey("name") &&
                                extras.containsKey("calories")) {
                    btnAccept.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String date = extras.getString("date");
                            Integer id = Integer.valueOf(extras.getString("activity_id"));
                            String activityName = extras.getString("name");
                            try {
                                calculateDurationAndCalories();
                                dbController.insertOrUpdateActivityTable(date, id, activityName, duration, calories);
                                finish();
                            } catch (Exception e) {
                                error.setText(e.getMessage());
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }
    }

    private void calculateDurationAndCalories() throws Exception {
        if (minutes.length() == 0) {
            Toast.makeText(EditActivities.this, "Invalid minutes", Toast.LENGTH_SHORT).show();
            throw new Exception("Invalid minutes");
        }
        duration = Integer.valueOf(minutes.getText().toString());
        if (duration <= 0) {
            Toast.makeText(EditActivities.this, "Invalid minutes", Toast.LENGTH_SHORT).show();
            throw new Exception("Invalid minutes");
        }
        calories = Integer.valueOf(getIntent().getStringExtra("calories")) * duration;
    }
}