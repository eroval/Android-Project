package com.example.f95108_calisthenics_app;

import android.content.ContentValues;

import java.text.DecimalFormat;

public class UserModel {
    private Integer id;
    private Double height;
    private Double  weight;

    private UserModel(){}

    public UserModel(Double  height, Double  weight) throws Exception {
        this.id=1;
        setHeight(height);
        setWeight(weight);
    }

    public void setHeight(Double  height) throws Exception{
        if (height<=0 || height>=600){
            throw new Exception("Invalid height.");
        }
        else {
            DecimalFormat df = new DecimalFormat("#.##");
            this.height  = Double.parseDouble(df.format(height));
        }
    }

    public void setWeight(Double  weight) throws Exception{
        if (weight<=0 || weight>=600){
            throw new Exception("Invalid weight.");
        }
        else {
            DecimalFormat df = new DecimalFormat("#.##");
            this.weight  = Double.parseDouble(df.format(weight));
        }
    }

    public Double getHeight(){
        return this.height;
    }

    public Double getWeight(){
        return this.weight;
    }

    public ContentValues getContentValues(){
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.UserTable.COLUMN_ID, this.id);
        values.put(DatabaseContract.UserTable.COLUMN_HEIGHT, this.height);
        values.put(DatabaseContract.UserTable.COLUMN_WEIGHT, this.weight);

        return values;
    }
}
