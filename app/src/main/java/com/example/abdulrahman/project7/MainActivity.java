package com.example.abdulrahman.project7;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {
    ListView listView;
    NewsApadter apadter;
    List<News> newsList;
    TextView msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_new);
        msg = findViewById(R.id.textView3);
        newsList = new ArrayList<>();

    }

    @Override
    protected void onResume() {
        super.onResume();
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            getSupportLoaderManager().initLoader(0, null, this).forceLoad();
        } else {
            msg.setText(R.string.noIntremt);
        }
        apadter = new NewsApadter(this, newsList);
        listView.setAdapter(apadter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri webpage = Uri.parse(newsList.get(position).getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);

                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                startActivity(new Intent(this, Setting.class));
                finish();

        }
        return super.onOptionsItemSelected(item);
    }


    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int id, @Nullable Bundle args) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String section = sharedPreferences.getString("settingActivity", "news");
        String sec = section.toLowerCase();
        editor.putString("sec", sec);
        editor.commit();
        String url = "http://content.guardianapis.com/search?&show-tags=contributor&q=debates&section=" + sec + "&api-key=3790bba9-e24a-4e1c-aa78-2cd5aa8bd76e";

        return new NewsLoader(this, url);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> data) {
        if (data.isEmpty()) {
            msg.setText(R.string.noData);
        } else {
            newsList = data;
            apadter = new NewsApadter(this, newsList);
            listView.setAdapter(apadter);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {

    }
}
