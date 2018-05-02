package com.example.abdulrahman.project7;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    public NewsLoader(@NonNull Context context) {
        super(context);

    }

    @Nullable
    @Override
    public List<News> loadInBackground() {
        List<News> newsList = new ArrayList<>();

        try {
            SharedPreferences preferences = getContext().getSharedPreferences("section", Context.MODE_PRIVATE);
            String sections = preferences.getString("sec", "news");
            String url = "http://content.guardianapis.com/search?&show-tags=contributor&q=debates&section=" + sections + "&api-key=3790bba9-e24a-4e1c-aa78-2cd5aa8bd76e";

            URL url1 = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.connect();
            if (connection.getResponseCode() == 200) {
                InputStream inputStream = connection.getInputStream();
                newsList = listNewsJson(inputStream);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    public List<News> listNewsJson(InputStream inputStream) throws IOException, JSONException {
        List<News> newsList = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        String name = "";
        StringBuilder builder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line);

        }
        JSONObject object = new JSONObject(builder.toString());
        JSONObject response = object.getJSONObject("response");
        JSONArray results = response.getJSONArray("results");
        for (int i = 0; i < results.length(); i++) {
            JSONObject c = results.getJSONObject(i);
            String title = c.getString("webTitle");
            String section = c.getString("sectionName");
            String date = c.getString("webPublicationDate");
            String url = c.getString("webUrl");
            try {
                JSONArray array = c.getJSONArray("tags");
                JSONObject o = array.getJSONObject(0);
                name = o.getString("firstName") + " " + o.getString("lastName");
            } catch (Exception e) {

            }
            newsList.add(new News(title, section, date, url, name));
        }

        return newsList;
    }


}
