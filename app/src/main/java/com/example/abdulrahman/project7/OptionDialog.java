package com.example.abdulrahman.project7;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;


public class OptionDialog extends android.support.v4.app.DialogFragment implements View.OnClickListener {
    RadioGroup radioGroup;
    Button button;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.option, container, false);
        radioGroup = view.findViewById(R.id.group);
        button = view.findViewById(R.id.but);
        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        this.dismiss();
        String section = "";
        int group = radioGroup.getCheckedRadioButtonId();

        if (group == R.id.radiomusic) {
            section = "music";
        }

        if (group == R.id.radionetwork) {
            section = "news";
        }

        if (group == R.id.radiobusiness) {
            section = "business";
        }

        if (group == R.id.radioButton4) {
            section = "sport";
        }

        Setting setting = (Setting) getActivity();
        setting.addSection(section);

    }
}
