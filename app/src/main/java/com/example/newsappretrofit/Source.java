
package com.example.newsappretrofit;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source {


    @SerializedName("id")
    @Expose

    private String id;
    @SerializedName("name")
    @Expose

    private String name;






    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\n Source{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
