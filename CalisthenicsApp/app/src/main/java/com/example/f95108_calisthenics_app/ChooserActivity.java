package com.example.f95108_calisthenics_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class ChooserActivity extends AppCompatActivity {

    private final String API_KEY="evYS5tC+eVVak1jfEnfiVQ==98PEiEXYirp1FYwB";
    private String activity;
    private Button searchBtn;
    private TextView activityText;
    private TextView requestText;


    public ChooserActivity() throws MalformedURLException {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);
        searchBtn = findViewById(R.id.searchBtn);
        activityText = findViewById(R.id.activityText);
        requestText = findViewById(R.id.requestText);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity = activityText.getText().toString();
                requestText.setText(sendGetRequest());
            }
        });


    }

    private String readStream(InputStream stream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String inputLine;
        String content="";
        while ((inputLine = in.readLine()) != null) {
            content+=inputLine;
        }
        in.close();
        return content;
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