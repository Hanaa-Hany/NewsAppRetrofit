package com.example.newsappretrofit;

import androidx.room.TypeConverter;

import com.google.gson.Gson;


public class Convertors {

    @TypeConverter
    public static String ConvertSourceToString(Source source){
        return new Gson().toJson(source);
    }
    @TypeConverter
    public static Source ConvertSource(String source){
        return new Gson().fromJson(source,Source.class);
    }

}
