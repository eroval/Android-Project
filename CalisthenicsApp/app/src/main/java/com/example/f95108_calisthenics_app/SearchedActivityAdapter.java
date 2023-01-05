package com.example.f95108_calisthenics_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SearchedActivityAdapter extends ArrayAdapter<SearchedActivityModel> {

    public SearchedActivityAdapter(Context context, List<SearchedActivityModel> activitiesList) {
        super(context, 0, activitiesList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.searched_activities_list_item, parent, false);
        }

        SearchedActivityModel currentActivity = getItem(position);

        TextView name = listItemView.findViewById(R.id.name);
        name.setText(currentActivity.getName());
        TextView calories = listItemView.findViewById(R.id.calories);
        calories.setText(currentActivity.getCalories().toString());

        return listItemView;
    }
}
