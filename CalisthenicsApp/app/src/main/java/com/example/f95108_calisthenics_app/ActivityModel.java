package com.example.f95108_calisthenics_app;

import android.content.ContentValues;

public class ActivityModel {
    private String date;
    private Integer id;
    private String activityName;
    private Integer calories;

    public ActivityModel(String date, Integer id, String name, Integer  calories) throws Exception {
        this.date = date;
        this.id = id;
        this.activityName = name;
        setActivityCalories(calories);
    }

    private void setActivityCalories(Integer calories) throws Exception {
        if (calories<0){
            throw new Exception("Invalid calories value.");
        }

    }

    public ContentValues getContentValues(){
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.ActivityTable.COLUMN_DATE, this.date);
        values.put(DatabaseContract.ActivityTable.COLUMN_ID, this.id);
        values.put(DatabaseContract.ActivityTable.COLUMN_NAME, this.activityName);
        values.put(DatabaseContract.ActivityTable.COLUMN_CALORIES, this.calories);

        return values;
    }
    public String getDate(){
        return this.date;
    }

    public Integer getId(){
        return this.id;
    }

    public String getActivityName(){
        return this.activityName;
    }

    public Integer getCalories(){
        return this.calories;
    }
}
