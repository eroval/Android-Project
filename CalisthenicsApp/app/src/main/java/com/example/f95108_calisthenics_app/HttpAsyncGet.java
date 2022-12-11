package com.example.f95108_calisthenics_app;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpAsyncGet extends AsyncTask<String, Void, String> {

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

    @Override
    protected String doInBackground(String... params) {
        try {
            URL my_url = new URL(params[0]);

            HttpURLConnection connection = (HttpURLConnection) my_url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept","application/json");
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestProperty("X-Api-Key",params[1]);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int responseCode = connection.getResponseCode();
            if (responseCode==HttpURLConnection.HTTP_OK) {
                String content = readStream(connection.getInputStream());
                return content;
            }
            return "Problem with response";
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            return "Invalid url";
        }
        catch (ProtocolException e) {
            e.printStackTrace();
            return "Invalid request";
        }
        catch (IOException e) {
            e.printStackTrace();
            return "Bad Input/Output from request";
        }
    }

    protected void onPostExecute(String result){
        super.onPostExecute(result);
    }
}
