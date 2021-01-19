package com.example.newsappretrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

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
    RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    BottomNavigationView bottomNavigationView;
    Retrofit retrofit;
    NewsApi newsApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        onClicksBottomNav();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        newsApi=retrofit.create(NewsApi.class);

        newsApi.getQueryNews("eg","technology")
                .enqueue(new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                        if(response.body()!=null && response.isSuccessful()){
                            Log.i(TAG, "onResponse: "+response.body().toString());
                            //list
                            NewsResponse list = response.body();
                            newsAdapter=new NewsAdapter(MainActivity.this,list.getArticles());
                            recyclerView.setAdapter(newsAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {

                        String errorMessage=t.getLocalizedMessage();
                        Log.i(TAG, "onFailure: "+errorMessage);
                    }
                });

    }

    private void onClicksBottomNav() {
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.item_sport){
                    changeFragment(new SportFragment());
                    newsApi.getQueryNews("eg","sport")
                            .enqueue(new Callback<NewsResponse>() {
                                @Override
                                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                                    if(response.body()!=null && response.isSuccessful()){
                                        Log.i(TAG, "onResponse: "+response.body().toString());
                                        //list
                                        NewsResponse list = response.body();
                                        newsAdapter=new NewsAdapter(MainActivity.this,list.getArticles());
                                        recyclerView.setAdapter(newsAdapter);
                                    }
                                }

                                @Override
                                public void onFailure(Call<NewsResponse> call, Throwable t) {

                                    String errorMessage=t.getLocalizedMessage();
                                    Log.i(TAG, "onFailure: "+errorMessage);
                                }
                            });
                }else if(id==R.id.item_technology){
                    changeFragment(new TechnologyFragment());
                    newsApi.getQueryNews("eg","technology")
                            .enqueue(new Callback<NewsResponse>() {
                                @Override
                                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                                    if(response.body()!=null && response.isSuccessful()){
                                        Log.i(TAG, "onResponse: "+response.body().toString());
                                        //list
                                        NewsResponse list = response.body();
                                        newsAdapter=new NewsAdapter(MainActivity.this,list.getArticles());
                                        recyclerView.setAdapter(newsAdapter);
                                    }
                                }

                                @Override
                                public void onFailure(Call<NewsResponse> call, Throwable t) {

                                    String errorMessage=t.getLocalizedMessage();
                                    Log.i(TAG, "onFailure: "+errorMessage);
                                }
                            });
                }else if(id==R.id.item_business){
                    changeFragment(new BussinessFragment());
                    newsApi.getQueryNews("eg","business")
                            .enqueue(new Callback<NewsResponse>() {
                                @Override
                                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                                    if(response.body()!=null && response.isSuccessful()){
                                        Log.i(TAG, "onResponse: "+response.body().toString());
                                        //list
                                        NewsResponse list = response.body();
                                        newsAdapter=new NewsAdapter(MainActivity.this,list.getArticles());
                                        recyclerView.setAdapter(newsAdapter);
                                    }
                                }

                                @Override
                                public void onFailure(Call<NewsResponse> call, Throwable t) {

                                    String errorMessage=t.getLocalizedMessage();
                                    Log.i(TAG, "onFailure: "+errorMessage);
                                }
                            });
                }else {
                    changeFragment(new ScienceFragment());
                    newsApi.getQueryNews("eg","science")
                            .enqueue(new Callback<NewsResponse>() {
                                @Override
                                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                                    if(response.body()!=null && response.isSuccessful()){
                                        Log.i(TAG, "onResponse: "+response.body().toString());
                                        //list
                                        NewsResponse list = response.body();
                                        newsAdapter=new NewsAdapter(MainActivity.this,list.getArticles());
                                        recyclerView.setAdapter(newsAdapter);
                                    }
                                }

                                @Override
                                public void onFailure(Call<NewsResponse> call, Throwable t) {

                                    String errorMessage=t.getLocalizedMessage();
                                    Log.i(TAG, "onFailure: "+errorMessage);
                                }
                            });
                }
            }
        });
    }

    private void initViews() {
        recyclerView=findViewById(R.id.recycler);
        bottomNavigationView=findViewById(R.id.bottom_nav);
        retrofit=new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
    private void changeFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.continer,fragment)
                .commit();
    }

}