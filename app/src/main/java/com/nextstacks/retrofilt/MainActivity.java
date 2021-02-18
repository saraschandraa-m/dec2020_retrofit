package com.nextstacks.retrofilt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import model.Article;
import model.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NewsCategoryAdapter.NewsCategoryListener {

    ProgressDialog progressDialog;

    RecyclerView mRcNewsList;
    RecyclerView mRcNewsCategory;

    String[] newsCats = new String[]{"business", "entertainment", "general", "health", "science", "sports", "technology"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FloatingActionButton fab = findViewById(R.id.fab_get_news);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getTopHeadlines();
//            }
//        });

        mRcNewsList = findViewById(R.id.rc_news_list);
        mRcNewsList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        mRcNewsCategory = findViewById(R.id.rc_news_categories);
        mRcNewsCategory.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));


        ArrayList<String> newsCategories = new ArrayList<String>(Arrays.asList(newsCats));

        NewsCategoryAdapter categoryAdapter = new NewsCategoryAdapter(this, newsCategories);
        categoryAdapter.setListener(this);
        mRcNewsCategory.setAdapter(categoryAdapter);

        progressDialog = new ProgressDialog(this);

        getTopHeadlines("");
    }


    private void getTopHeadlines(String category) {
        progressDialog.setTitle("Getting Top Headlines");
        progressDialog.show();

        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);

        HashMap<String, Object> queries = new HashMap<>();
        queries.put("apiKey", "4c82d7e8131841f484c6cf169bb83ae4");
        if (category.isEmpty()) {
            queries.put("sources", "bbc-news");
        } else {
            queries.put("category", category);
        }

        Call<String> getTopHeadlines = apiInterface.getNews(queries);

        getTopHeadlines.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progressDialog.hide();
                Log.i("API-RESPONSE", "Success");

                Log.i("API-RESPONSE", response.body());

                ArrayList<Article> articles = News.parseNewsValue(response.body()).articles;
                loadNewsData(articles);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progressDialog.hide();
                Log.i("API-RESPONSE", "Failed");
            }
        });
    }

    private void loadNewsData(ArrayList<Article> articles) {
        NewsListAdapter adapter = new NewsListAdapter(MainActivity.this, articles);
        mRcNewsList.setAdapter(adapter);
    }

    @Override
    public void onNewsCategorySelected(String category) {
        getTopHeadlines(category);
    }
}