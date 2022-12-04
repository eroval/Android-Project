package com.example.f95108_calisthenics_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    // btns
    private Button bodyBtn;
    private Button activityBtn;

    // fragments
    private Fragment bodyFragment = new BodyFragment();
    private Fragment activitiesFragment = new ActivitiesFragment();
    private String bodyFragmentTag = "bodyFragmentTag";
    private String activitiesFragmentTag = "activitiesFragmentTag";

    private FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bodyBtn = findViewById(R.id.btnBody);
        activityBtn = findViewById(R.id.btnActivity);
        fManager = getSupportFragmentManager();

//        bodyBtn.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               getSupportFragmentManager().beginTransaction()
//                       .replace(R.id.fragmentContainerView2, bodyFragment)
//                       .addToBackStack(null)
//                       .commit();
//           }
//        });
//
//        activityBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragmentContainerView2, activitiesFragment)
//                        .addToBackStack(null)
//                        .commit();
//            }
//        });
        bodyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = fManager.findFragmentByTag(bodyFragmentTag);
                if (fragment!=null) fManager.popBackStack
                        (bodyFragmentTag, fManager.POP_BACK_STACK_INCLUSIVE);
                fManager.beginTransaction()
                        .replace(R.id.fragmentContainerView2, bodyFragment, bodyFragmentTag)
                        .setReorderingAllowed(true)
                        .addToBackStack(bodyFragmentTag)
                        .commit();
            }
        });


        activityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = fManager.findFragmentByTag(activitiesFragmentTag);
                if (fragment!=null) fManager.popBackStack
                        (activitiesFragmentTag, fManager.POP_BACK_STACK_INCLUSIVE);
                fManager.beginTransaction()
                        .replace(R.id.fragmentContainerView2, activitiesFragment, activitiesFragmentTag)
                        .setReorderingAllowed(true)
                        .addToBackStack(activitiesFragmentTag)
                        .commit();
            }
        });
//        bodyBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                fragmentManager.beginTransaction();
//            }
//        });

    }
}