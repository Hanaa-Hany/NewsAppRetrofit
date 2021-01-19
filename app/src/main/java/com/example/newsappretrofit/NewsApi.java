package com.example.newsappretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("v2/top-headlines?country=us&category=business&apiKey=e825cc8cb2e246f5a39dbe2d34dffcef")
    Call<NewsResponse> getNews();

    @GET("v2/top-headlines?apiKey=e825cc8cb2e246f5a39dbe2d34dffcef")
    Call<NewsResponse>getQueryNews(@Query("country") String country,
                                   @Query("category") String category);
}
