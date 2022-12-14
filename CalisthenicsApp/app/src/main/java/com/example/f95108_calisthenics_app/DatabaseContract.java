package com.example.f95108_calisthenics_app;

import android.content.ContentValues;
import android.content.Context;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public final class DatabaseContract {
    public static final String DATABASE_NAME="F95108_Calisthenics.db";
    public static final Integer DATABASE_VERSION = 1;

    // types
    private static final String TEXT = " TEXT, ";
    private static final String INTEGER = " INTEGER, ";

    private DatabaseContract(){}

    // Table
    public static class UserTable implements BaseColumns {
        public static final String TABLE_NAME = "user_table";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_HEIGHT = "height";
        public static final String COLUMN_WEIGHT = "weight";

        public static  final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" +
                COLUMN_ID + INTEGER +
                COLUMN_HEIGHT + INTEGER +
                COLUMN_WEIGHT + INTEGER +
                "PRIMARY KEY (" + COLUMN_ID + ")" +
                ")";

        public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    }

    public static class ActivityTable implements BaseColumns {
        public static final String TABLE_NAME = "user_activities_table";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DURATION = "duration";
        public static final String COLUMN_CALORIES = "calories";

        public static  final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" +
                COLUMN_DATE + TEXT +
                COLUMN_ID + INTEGER +
                COLUMN_NAME + TEXT +
                COLUMN_DURATION + INTEGER +
                COLUMN_CALORIES + INTEGER +
                "PRIMARY KEY (" + COLUMN_DATE + "," + COLUMN_ID + ")"
                + ")";

        public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
