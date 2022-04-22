package com.example.newsappretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsDetailsActivity extends AppCompatActivity {
//sending data from fragment to activity
    WebView imageView;
    TextView textViewTitle,textViewDescription,textViewContent,textViewAuther,textViewPublishAt;
    private static final String TAG = "NewsDetailsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        initViews();
        int itemId=getIntent().getIntExtra("Position",-1);
        textViewTitle.setText(getIntent().getStringExtra("Title"));
        textViewDescription.setText(getIntent().getStringExtra("Description"));
        textViewPublishAt.setText(getIntent().getStringExtra("Publish At"));
        textViewAuther.setText(getIntent().getStringExtra("Auther"));
        //textViewContent.setText(getIntent().getStringExtra("Content"));

        imageView.loadUrl(getIntent().getExtras().getString("Image"));
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();


       // imageView.loadDataWithBaseURL(getIntent().getExtras().getString("Image"),"<style>img{display: inline;height: auto;max-width: 100%;}</style>" ,"text/html", "UTF-8",  getIntent().getExtras().getString("Image"));
        Log.i(TAG, "onCreate: "+itemId);
        


    }

    private void initViews() {
        imageView=findViewById(R.id.image_details);
        textViewDescription=findViewById(R.id.tv_description_details);
        textViewTitle=findViewById(R.id.tv_title_details);
        textViewAuther=findViewById(R.id.tv_Auther_details);
        textViewPublishAt=findViewById(R.id.tv_publish_details);
        textViewContent=findViewById(R.id.tv_content_details);

    }
}