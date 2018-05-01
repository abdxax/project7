package com.example.abdulrahman.project7;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NewsApadter extends ArrayAdapter<News> {
    List<News> newsList;

    public NewsApadter(@NonNull Context context, List<News> newsList) {
        super(context, 0, newsList);
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.list_news, parent, false);
        TextView title = view.findViewById(R.id.titlenews);
        TextView section = view.findViewById(R.id.sectionnews);
        TextView date = view.findViewById(R.id.datenews);
        title.setText(newsList.get(position).getTitle());
        section.setText(newsList.get(position).getSection());
        date.setText(newsList.get(position).getDate());

        return view;
    }
}
