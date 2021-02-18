package com.nextstacks.retrofilt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import model.Article;
import model.News;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListHolder> {

    private Context context;
    private ArrayList<Article> newsList;

    public NewsListAdapter(Context context, ArrayList<Article> newsList) {
        this.context = context;
        this.newsList = newsList;
    }


    @NonNull
    @Override
    public NewsListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        NewsListHolder holder = new NewsListHolder(LayoutInflater.from(context).inflate(R.layout.cell_news, parent, false))
//        return holder;

        return new NewsListHolder(LayoutInflater.from(context).inflate(R.layout.cell_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListHolder holder, int position) {
        Article item = newsList.get(position);

        holder.mTvTitle.setText(item.title);
        holder.mTvDescription.setText(item.description);

        Glide.with(context).load(item.urlToImage).into(holder.mIvNews);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class NewsListHolder extends RecyclerView.ViewHolder {

        private TextView mTvTitle;
        private TextView mTvDescription;
        private ImageView mIvNews;

        public NewsListHolder(@NonNull View itemView) {
            super(itemView);

            mTvTitle = itemView.findViewById(R.id.tv_news_title);
            mTvDescription = itemView.findViewById(R.id.tv_news_desc);

            mIvNews = itemView.findViewById(R.id.iv_news_img);
        }
    }
}
