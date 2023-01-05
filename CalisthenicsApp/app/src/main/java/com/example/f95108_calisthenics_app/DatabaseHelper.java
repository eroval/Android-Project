package com.example.f95108_calisthenics_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private SQLiteDatabase database;

    public DatabaseHelper(@Nullable Context context){
        super(context, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);
        this.context=context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseContract.UserTable.CREATE_TABLE);
        sqLiteDatabase.execSQL(DatabaseContract.ActivityTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int old_v, int new_v) {
        sqLiteDatabase.execSQL(DatabaseContract.UserTable.DROP_TABLE);
        sqLiteDatabase.execSQL(DatabaseContract.ActivityTable.DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    public boolean checkUserExistence(){
        String[] columns = new String[] {
                DatabaseContract.UserTable.COLUMN_ID,
                DatabaseContract.UserTable.COLUMN_HEIGHT,
                DatabaseContract.UserTable.COLUMN_WEIGHT};
        Cursor cursor = this.getReadableDatabase().query(DatabaseContract.UserTable.TABLE_NAME,columns, null, null, null, null, null);
        if (cursor!=null){
            return true;
        }
        return false;
    }

    public boolean insertOrUpdateUserTable(Double height, Double weight){
        try {
            UserModel user = new UserModel(height, weight);
            ContentValues values = user.getContentValues();

            this.getWritableDatabase().replace(DatabaseContract.UserTable.TABLE_NAME, null, values);
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean insertOrUpdateActivityTable(String date, Integer id, String activityName, Integer duration, Integer calories){
        try {
            ActivityModel activity = new ActivityModel(date, id, activityName, duration, calories);
            ContentValues values = activity.getContentValues();

            this.getWritableDatabase().replace(DatabaseContract.ActivityTable.TABLE_NAME, null, values);
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public UserModel getUser(){
        UserModel user = null;
        Cursor c = this.getReadableDatabase().query(
                DatabaseContract.UserTable.TABLE_NAME,
                new String[]{
                        DatabaseContract.UserTable.COLUMN_ID,
                        DatabaseContract.UserTable.COLUMN_HEIGHT,
                        DatabaseContract.UserTable.COLUMN_WEIGHT
                },
                DatabaseContract.UserTable.COLUMN_ID + "=?",
                new String[]{String.valueOf(1)},
                null, null, null, null);
        if (c!=null){
            c.moveToFirst();
        }
        try { user = new UserModel(c.getDouble(1),c.getDouble(2) );
        } catch (Exception e) {
            System.out.println("Couldn't fetch user");
            e.printStackTrace();
        }
        c.close();
        return user;
    }
    public String getDateInSuitableFormat(Date date){
        SimpleDateFormat curFormat = new SimpleDateFormat("yyyy/MM/dd");
        return curFormat.format(date);
    }

    public Integer findActivityIdToInsert(String date){
        Cursor c = this.getReadableDatabase().query(
                DatabaseContract.ActivityTable.TABLE_NAME,
                new String[]{
                        DatabaseContract.ActivityTable.COLUMN_DATE,
                        DatabaseContract.ActivityTable.COLUMN_ID,
                        DatabaseContract.ActivityTable.COLUMN_NAME,
                        DatabaseContract.ActivityTable.COLUMN_DURATION,
                        DatabaseContract.ActivityTable.COLUMN_CALORIES
                },
                DatabaseContract.ActivityTable.COLUMN_DATE + "=?",
                new String[]{date},
                null, null, null, null);
        if (c!=null && c.getCount()>0){
            c.moveToLast();
            Integer id = c.getInt(1);
            return id+1;
        }
        c.close();
        return 1;
    }


    public ArrayList<ActivityModel> getActivities(String date) throws Exception {
        ArrayList<ActivityModel> activitiesList = new ArrayList<>();
        Cursor c = this.getReadableDatabase().rawQuery("SELECT * FROM " + DatabaseContract.ActivityTable.TABLE_NAME, null);
        if (c.moveToFirst()){
            do {
                ActivityModel currentActivity = new ActivityModel(
                        c.getString(0),
                        c.getInt(1),
                        c.getString(2),
                        c.getInt(3),
                        c.getInt(4));
                activitiesList.add(currentActivity);
            } while(c.moveToNext());
        }
        c.close();
        return activitiesList;
    }

}
