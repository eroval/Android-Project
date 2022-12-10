package com.example.f95108_calisthenics_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

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

    public boolean InsertOrUpdateUserTable(Double height, Double weight){
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

}
