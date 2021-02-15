package com.nextstacks.retrofilt;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface APIInterface {

    /**
     * 1. Get
     * 2. Post
     * 3. Put
     * 4. Delete
     */


    @GET("top-headlines/")
    Call<String> getNews(@QueryMap Map<String, Object> queries);
}
