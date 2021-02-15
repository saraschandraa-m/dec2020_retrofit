package com.nextstacks.retrofilt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import model.Article;
import model.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab_get_news);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTopHeadlines();
            }
        });
    }


    private void getTopHeadlines(){


        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);

        HashMap<String, Object> queries = new HashMap<>();
        queries.put("apiKey", "4c82d7e8131841f484c6cf169bb83ae4");
        queries.put("sources", "bbc-news");

        Call<String> getTopHeadlines = apiInterface.getNews(queries);

        getTopHeadlines.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("API-RESPONSE", "Success");

                Log.i("API-RESPONSE", response.body());

                ArrayList<Article> articles= News.parseNewsValue(response.body()).articles;
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("API-RESPONSE", "Failed");
            }
        });
    }

}