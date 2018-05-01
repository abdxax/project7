package com.example.abdulrahman.project7;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Setting extends AppCompatActivity {
    TextView textView;
    SharedPreferences preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        textView = (TextView) findViewById(R.id.textView2);
        preference = this.getSharedPreferences("section", Context.MODE_PRIVATE);
        String secn = preference.getString("sec", "news");
        if (secn != null) {
            textView.setText(secn);
        }
    }

    public void butSetting(View view) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        OptionDialog optionDialog = new OptionDialog();
        optionDialog.show(fragmentManager, "TAG");

    }

    public void addSection(String Section) {
        SharedPreferences.Editor editor = preference.edit();
        editor.putString("sec", Section);
        editor.commit();
        textView.setText(preference.getString("sec", null));
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
