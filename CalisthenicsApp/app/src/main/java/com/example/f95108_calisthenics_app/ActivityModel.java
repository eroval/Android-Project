package com.example.f95108_calisthenics_app;

import android.content.ContentValues;

public class ActivityModel {
    private String date;
    private Integer id;
    private String activityName;
    private Integer duration;
    private Integer calories;

    public ActivityModel(String date, Integer id, String name, Integer duration, Integer  calories) throws Exception {
        this.date = date;
        this.id = id;
        this.activityName = name;
        this.duration = duration;
        setActivityCalories(calories);
    }

    private void setActivityCalories(Integer calories) throws Exception {
        if (calories<0){
            throw new Exception("Invalid calories value.");
        }
        this.calories = calories;
    }

    public ContentValues getContentValues(){
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.ActivityTable.COLUMN_DATE, this.date);
        values.put(DatabaseContract.ActivityTable.COLUMN_ID, this.id);
        values.put(DatabaseContract.ActivityTable.COLUMN_NAME, this.activityName);
        values.put(DatabaseContract.ActivityTable.COLUMN_DURATION, this.duration);
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

    public Integer getDuration(){return this.duration;}

    public Integer getCalories(){
        return this.calories;
    }

    public String toString(){
        return date + "\n"
                + id + "\n"
                + activityName + "\n"
                + duration + "\n"
                + calories + "\n";
    }
}
