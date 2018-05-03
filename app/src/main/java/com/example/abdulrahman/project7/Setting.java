package com.example.abdulrahman.project7;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.app.Fragment;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Setting extends AppCompatActivity {

    SharedPreferences preference;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        preference = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences = this.getSharedPreferences("section", MODE_PRIVATE);
        Fragment fragment = new SettingFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if (savedInstanceState == null) {
            fragmentTransaction.add(R.id.activeseeting, fragment, "deeting_fragment");
            fragmentTransaction.commit();
        } else {
            fragment = getFragmentManager().findFragmentByTag("deeting_fragment");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch (preference.getString("settingActivity", "news")) {
            case "News":
                editor.putString("sec", "news");
                editor.commit();

                break;
            case "Business":
                editor.putString("sec", "business");
                editor.commit();
                break;
            case "sport":
                editor.putString("sec", "sport");
                editor.commit();
                break;
            case "Music":
                editor.putString("sec", "music");
                editor.commit();
                break;
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveseeting:
                startActivity(new Intent(this, MainActivity.class));
                finish();

        }
        return super.onOptionsItemSelected(item);
    }
    public static class SettingFragment extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_scren);


        }
    }
}
