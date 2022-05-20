package com.example.newsappretrofit;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface NewsDAO {

    @Insert
    void addNewsArticle(Article article);

    @Query("SELECT * FROM `Article DB`")
    List<Article> getAllArticle();


    @Query("DELETE FROM `Article DB` WHERE  (SELECT id FROM `Article DB`  LIMIT -1 OFFSET 20)")
    void removeOldData();
}
