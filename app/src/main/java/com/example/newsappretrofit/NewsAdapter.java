package com.example.newsappretrofit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter <NewsAdapter.NewsViewHolder>{

    Context context;
    List<Article>arrayList;

    private static final String TAG = "NewsAdapter";

    public NewsAdapter(Context context, List<Article> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        NewsViewHolder newsViewHolder=new NewsViewHolder(view);
        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article article=arrayList.get(position);

        String title=article.getTitle();
        String image=article.getUrlToImage();
        String description=article.getDescription();
        String content=article.getContent();
        String date=article.getPublishedAt();
        String auther=article.getAuthor();


        holder.textViewTitel.setText(title);
        //holder.textViewDescription.setText(description);
        Picasso.get()
                .load(image)
                .into(holder.imageView);
        Log.i(TAG, "onBindViewHolder: ");
        Log.i(TAG, "onBindViewHolder: "+image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: ");
                int positionItem=position;

                Intent intent=new Intent(context,NewsDetailsActivity.class);
                intent.putExtra("Position",positionItem);
                intent.putExtra("Title",title);
                intent.putExtra("Content",content);
                intent.putExtra("Publish At",date);
                intent.putExtra("Description",description);
                intent.putExtra("Auther",auther);
                Log.i(TAG, "onClick: "+content);
                intent.putExtra("Image",image);
                context.startActivity(intent);

                Log.i(TAG, "onClick: "+positionItem);



            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTitel,textViewDescription;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_news);
            textViewTitel=itemView.findViewById(R.id.tv_title);
            //textViewDescription=itemView.findViewById(R.id.tv_description);

        }
    }
}
