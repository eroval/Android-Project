package com.example.f95108_calisthenics_app;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.os.IBinder;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyDatabaseService extends Service {
    private Date currentDate;
    private DatabaseHelper dbController;

    public MyDatabaseService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
//        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate(){
        super.onCreate();
        dbController = new DatabaseHelper(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Date date = new Date();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DatabaseContract.OperationsTable.COLUMN_DATE, date.toString());
                    dbController.getWritableDatabase().replace(DatabaseContract.OperationsTable.TABLE_NAME, null,contentValues);
                }
                catch (Exception e){
                    System.out.println("Couldn't save");
                }
            }
        });
        executorService.shutdown();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

    }
}