package com.example.newsappretrofit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ArrayList<Article> articleArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    BottomNavigationView bottomNavigationView;
    Retrofit retrofit;
    NewsApi newsApi;
    Source source;
    String author;
    String title;
    String description;
    String urlToImage;
    String content;
    String url;
    String publishedAt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        onClicksBottomNav();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


        newsApi = retrofit.create(NewsApi.class);




        newsApi.getQueryNews("eg", "sport")
                .enqueue(new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                        if (response.body() != null && response.isSuccessful()) {
                            Log.i(TAG, "onResponse: " + response.body().toString());
                            //list
                            NewsResponse list = response.body();

                            // NewsRoomDB.getInstance(MainActivity.this).newsDAO().addNewsArticle((Article) list.getArticles());

                            newsAdapter = new NewsAdapter(MainActivity.this, list.getArticles());
                            recyclerView.setAdapter(newsAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {

                        String errorMessage = t.getLocalizedMessage();

                        Log.i(TAG, "onFailure: " + errorMessage);
                    }
                });


    }

    private void onClicksBottomNav() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.item_sport) {
                    changeFragment(new SportFragment());
                    boolean connected = false;
                    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                        //we are connected to a network
                        connected = true;
                        newsApi.getQueryNews("eg", "sport")
                                .enqueue(new Callback<NewsResponse>() {
                                    @Override
                                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                                        if (response.body() != null && response.isSuccessful()) {
                                            Log.i(TAG, "onResponse: " + response.body().toString());
                                            //list
                                            NewsResponse list = response.body();

                                            for (int i = 0; i < response.body().getArticles().size(); i++) {
                                                source = response.body().getArticles().get(i).getSource();
                                                author = response.body().getArticles().get(i).getAuthor();
                                                title = response.body().getArticles().get(i).getTitle();
                                                description = response.body().getArticles().get(i).getDescription();
                                                url = response.body().getArticles().get(i).getUrl();
                                                urlToImage = response.body().getArticles().get(i).getUrlToImage();
                                                publishedAt = response.body().getArticles().get(i).getPublishedAt();
                                                content = response.body().getArticles().get(i).getContent();
                                                Article article = new Article(source, author, title, description, url, urlToImage, publishedAt, content);

                                                NewsRoomDB.getInstance(MainActivity.this).newsDAO().addNewsArticle(article);
                                                NewsRoomDB.getInstance(MainActivity.this).newsDAO().removeOldData();
                                                Log.i(TAG, "onResponse: removed data");
                                            }
                                            newsAdapter = new NewsAdapter(MainActivity.this, list.getArticles());
                                            recyclerView.setAdapter(newsAdapter);

                                        }
                                    }


                                    @Override
                                    public void onFailure(Call<NewsResponse> call, Throwable t) {
                                        String errorMessage = t.getLocalizedMessage();
                                        Log.i(TAG, "onFailure: " + errorMessage);
                                    }

                                });
                    }
                        else {
                            connected = false;


                            List<Article> news = NewsRoomDB.getInstance(MainActivity.this).newsDAO().getAllArticle();
                            newsAdapter = new NewsAdapter(MainActivity.this, news);
                            Log.i(TAG, "onResponse: " + news);
                            recyclerView.setAdapter(newsAdapter);
                        }


                } else if (id == R.id.item_technology) {
                    changeFragment(new TechnologyFragment());

                    boolean connected = false;
                    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                        //we are connected to a network
                        connected = true;
                    newsApi.getQueryNews("us", "technology")
                            .enqueue(new Callback<NewsResponse>() {
                                @Override
                                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                                    if (response.body() != null && response.isSuccessful()) {
                                        Log.i(TAG, "onResponse: " + response.body().toString());
                                        //list

                                        for (int i = 0; i < response.body().getArticles().size(); i++) {
                                            source = response.body().getArticles().get(i).getSource();
                                            author = response.body().getArticles().get(i).getAuthor();
                                            title = response.body().getArticles().get(i).getTitle();
                                            description = response.body().getArticles().get(i).getDescription();
                                            url = response.body().getArticles().get(i).getUrl();
                                            urlToImage = response.body().getArticles().get(i).getUrlToImage();
                                            publishedAt = response.body().getArticles().get(i).getPublishedAt();
                                            content = response.body().getArticles().get(i).getContent();
                                            Article article = new Article(source, author, title, description, url, urlToImage, publishedAt, content);

                                            NewsRoomDB.getInstance(MainActivity.this).newsDAO().addNewsArticle(article);
                                            NewsRoomDB.getInstance(MainActivity.this).newsDAO().removeOldData();
                                            Log.i(TAG, "onResponse: removed data");

                                        }
                                        NewsResponse list = response.body();
                                        newsAdapter = new NewsAdapter(MainActivity.this, list.getArticles());
                                        recyclerView.setAdapter(newsAdapter);
                                    }
                                }

                                    @Override
                                    public void onFailure(Call<NewsResponse> call, Throwable t) {
                                        String errorMessage = t.getLocalizedMessage();
                                        Log.i(TAG, "onFailure: " + errorMessage);
                                    }

                                });
                            }
                        else {
                            connected = false;


                            List<Article> news = NewsRoomDB.getInstance(MainActivity.this).newsDAO().getAllArticle();
                            newsAdapter = new NewsAdapter(MainActivity.this, news);
                            Log.i(TAG, "onResponse: " + news);
                            recyclerView.setAdapter(newsAdapter);
                        }


                    }

                else if (id == R.id.item_business) {
                    changeFragment(new BussinessFragment());
                    newsApi.getQueryNews("eg", "business")
                            .enqueue(new Callback<NewsResponse>() {
                                @Override
                                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                                    if (response.body() != null && response.isSuccessful()) {
                                        Log.i(TAG, "onResponse: " + response.body().toString());
                                        //list
                                        NewsResponse list = response.body();
                                        newsAdapter = new NewsAdapter(MainActivity.this, list.getArticles());
                                        recyclerView.setAdapter(newsAdapter);
                                    }
                                }

                                @Override
                                public void onFailure(Call<NewsResponse> call, Throwable t) {

                                    String errorMessage = t.getLocalizedMessage();
                                    Log.i(TAG, "onFailure: " + errorMessage);
                                }
                            });
                } else {
                    changeFragment(new ScienceFragment());
                    newsApi.getQueryNews("eg", "science")
                            .enqueue(new Callback<NewsResponse>() {
                                @Override
                                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                                    if (response.body() != null && response.isSuccessful()) {
                                        Log.i(TAG, "onResponse: " + response.body().toString());
                                        //list
                                        NewsResponse list = response.body();

                                        //Article article = new Article();
                                        //NewsRoomDB.getInstance(MainActivity.this).newsDAO().addNewsArticle(article);

                                        newsAdapter = new NewsAdapter(MainActivity.this, list.getArticles());
                                        recyclerView.setAdapter(newsAdapter);
                                    }
                                }

                                @Override
                                public void onFailure(Call<NewsResponse> call, Throwable t) {

                                    String errorMessage = t.getLocalizedMessage();
                                    Log.i(TAG, "onFailure: " + errorMessage);

                                }
                            });
                }
                return true;
            }

        });

    }


    private void initViews() {
        recyclerView = findViewById(R.id.recycler);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.continer, fragment)
                .commit();
    }


}