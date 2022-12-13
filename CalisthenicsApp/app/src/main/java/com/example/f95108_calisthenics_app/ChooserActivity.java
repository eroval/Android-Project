package com.example.f95108_calisthenics_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TaskStackBuilder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ChooserActivity extends AppCompatActivity {

    private final String API_KEY="evYS5tC+eVVak1jfEnfiVQ==98PEiEXYirp1FYwB";
    private String activity;
    private Button searchBtn;
    private TextView activityText;
    private ListView activitiesListView;
//    private TextView requestText;


    public ChooserActivity() throws MalformedURLException {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);
        searchBtn = findViewById(R.id.searchBtn);
        activityText = findViewById(R.id.activityText);
        activitiesListView = findViewById(R.id.activitiesList);

//        requestText = findViewById(R.id.requestText);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity = activityText.getText().toString();
                String response = sendGetRequest();
                JSONObject responseJson = getJSONObject(response);
                ArrayList<SearchedActivityModel> activities = getActivitiesFromJsonObject(responseJson);
                if (responseJson!=null && activities!=null) {
                    SearchedActivityAdapter activityAdapter = new SearchedActivityAdapter(ChooserActivity.this, activities);
                    activitiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            SearchedActivityModel currentActivity = activityAdapter.getItem(position);
                            Intent intent = new Intent(ChooserActivity.this, EditActivities.class);
                            intent.putExtra("name", currentActivity.getName());
                            intent.putExtra("calories", Integer.valueOf(currentActivity.getCalories()/60).toString());
                            TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(ChooserActivity.this);
                            taskStackBuilder.addNextIntentWithParentStack(intent);
                            taskStackBuilder.startActivities();
                        }
                    });
                    activitiesListView.setAdapter(activityAdapter);
                }
                else{
//                requestText.setText("Problem with Json Object");
                }
            }
        });


    }

    private ArrayList<SearchedActivityModel> getActivitiesFromJsonObject(JSONObject jsonObject){
        try {
            JSONArray activitiesJson = jsonObject.getJSONArray("response");
            ArrayList<SearchedActivityModel> activitiesArray = new ArrayList<>();
            for (int i = 0; i < activitiesJson.length(); i++) {
                JSONObject activity = activitiesJson.getJSONObject(i);
                SearchedActivityModel currentActivity = new SearchedActivityModel(activity.getString("name"),
                                                                                  activity.getInt("calories_per_hour"));
                activitiesArray.add(currentActivity);
            }
            return activitiesArray;
        }
        catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    private JSONObject getJSONObject(String response){
        try {
            JSONObject responseJson = responseToJsonObject(response);
            return responseJson;
            //return responseJson.toString();
        }
        catch (JSONException  e){
            e.printStackTrace();
            return null;
        }
    }

    private JSONObject responseToJsonObject(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray(response);
        jsonObject.put("response", jsonArray);
        return jsonObject;
    }

    private String sendGetRequest() {
        String url = "https://api.api-ninjas.com/v1/caloriesburned?activity=" + activity;
        HttpAsyncGet request = new HttpAsyncGet();
        try {
            String result = request.execute(url, API_KEY).get();
            return result;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return "Problem executing async request";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "Problem with Async";
        }
    }
}