
package com.example.newsappretrofit;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Article DB")

public class Article {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @TypeConverters(Convertors.class)
    @NonNull
    @SerializedName("source")
    @Expose

    @ColumnInfo(name = "source")
    private Source source;
    @SerializedName("author")
    @Expose
    @ColumnInfo(name = "Author")

    private String author;
    @SerializedName("title")
    @Expose
    @ColumnInfo(name = "Title")

    private String title;

    @SerializedName("description")
    @Expose
    @ColumnInfo(name = "Description")

    private String description;

    @SerializedName("url")
    @Expose
    @ColumnInfo(name = "URL")

    private String url;

    @SerializedName("urlToImage")
    @Expose
    @ColumnInfo(name = "URL to Image")

    private String urlToImage;

    @SerializedName("publishedAt")
    @Expose
    @ColumnInfo(name = "Published At")

    private String publishedAt;

    @SerializedName("content")
    @Expose
    @ColumnInfo(name = "Content")

    private String content;



    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Article(@NonNull Source source, String author, String title, String description, String url, String urlToImage, String publishedAt, String content) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    @Override
    public String toString() {
        return "\nArticle{" +
                "source=" + source +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", content='" + content + '\'' +
                '}';
    }


}
