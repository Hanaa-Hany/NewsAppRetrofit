package com.example.newsappretrofit;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(version = 1,entities = {Article.class},exportSchema = false)

public abstract class NewsRoomDB extends RoomDatabase {

    private static NewsRoomDB instance;

    public static NewsRoomDB getInstance(Context context){

        if(instance==null){
            instance= Room
                    .databaseBuilder(context,NewsRoomDB.class,"News DB")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract NewsDAO newsDAO();
}
