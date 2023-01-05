package com.example.f95108_calisthenics_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ActivityModelAdapter extends ArrayAdapter<ActivityModel> {
    public ActivityModelAdapter(Context context, List<ActivityModel> activitiesList) {
        super(context, 0, activitiesList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_list_item, parent, false);
        }

        ActivityModel currentActivity = getItem(position);

        TextView date = listItemView.findViewById(R.id.date);
        date.setText(currentActivity.getDate());
        TextView name = listItemView.findViewById(R.id.name);
        name.setText(currentActivity.getActivityName());
        TextView calories = listItemView.findViewById(R.id.calories);
        calories.setText(currentActivity.getCalories().toString());

        return listItemView;
    }
}
