package com.example.f95108_calisthenics_app;

public class SearchedActivityModel {
    private String name;
    private Integer calories;

    public SearchedActivityModel(String name, Integer calories){
        this.name = name;
        this.calories = calories;
    }

    public String getName(){
        return this.name;
    }

    public Integer getCalories(){
        return this.calories;
    }
}
