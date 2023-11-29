package com.example.fitguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class WorkoutListAdapter extends ArrayAdapter<String> {

    public WorkoutListAdapter(Context context, String[] workouts) {
        super(context, 0, workouts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String item = getItem(position);

        if (item.startsWith("-->")) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_centered_title, parent, false);
            TextView textView = convertView.findViewById(R.id.centeredTitle);
            textView.setText(item);
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            TextView textView = convertView.findViewById(android.R.id.text1);
            textView.setText(item);
        }

        return convertView;
    }
}
