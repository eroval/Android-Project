package com.example.f95108_calisthenics_app;

import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActivitiesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivitiesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Activity activityContext;
    private FloatingActionButton addBtn;
    private DatabaseHelper dbController;
    private ListView activitiesListView;

    public ActivitiesFragment() {
        // Required empty public constructor
    }

    public ActivitiesFragment(Activity mainActivity) {
        this.activityContext = mainActivity;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActivitiesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActivitiesFragment newInstance(String param1, String param2) {
        ActivitiesFragment fragment = new ActivitiesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activities, container, false);
        addBtn = view.findViewById(R.id.addActivityBtn);
        activitiesListView = view.findViewById(R.id.activitiesFragmentList);
        dbController = new DatabaseHelper(activityContext);
//
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
                try {
                    String date = dbController.getDateInSuitableFormat(new Date());
                    ArrayList<ActivityModel> activities = dbController.getActivities(date);
                    if (activities != null) {
                        ActivityModelAdapter activityAdapter = new ActivityModelAdapter(activityContext, activities);
                        activitiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                ActivityModel currentActivity = activityAdapter.getItem(position);
                                Intent intent = new Intent(activityContext, EditActivities.class);
                                intent.putExtra("id", "2");
                                intent.putExtra("date", currentActivity.getDate());
                                intent.putExtra("activity_id", currentActivity.getId().toString());
                                intent.putExtra("name", currentActivity.getActivityName());
                                intent.putExtra("calories", Integer.valueOf(currentActivity.getCalories() / currentActivity.getDuration()).toString());
                                TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(activityContext);
                                Intent parentActivity = new Intent(activityContext, MainActivity.class);
                                taskStackBuilder.addNextIntent(parentActivity);
                                taskStackBuilder.addNextIntent(intent);
                                taskStackBuilder.startActivities();
                            }
                        });
                        activitiesListView.setAdapter(activityAdapter);
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
//            }
//        });


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secondaryIntent = new Intent(activityContext, ChooserActivity.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(activityContext);
                stackBuilder.addNextIntentWithParentStack(secondaryIntent);
                stackBuilder.startActivities();
            }
        });

        return view;
    }
}