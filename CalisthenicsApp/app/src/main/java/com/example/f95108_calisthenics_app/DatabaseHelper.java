package com.example.f95108_calisthenics_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public final class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;

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
}
