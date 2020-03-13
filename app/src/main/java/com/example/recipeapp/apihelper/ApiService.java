package com.example.recipeapp.apihelper;

import com.example.recipeapp.model.DetailResult;
import com.example.recipeapp.model.RecipeRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search")
    Call<RecipeRequest> getNewsList(@Query("apiKey") String apiKey);

    @GET("{id}/summary")
    Call<DetailResult> getDetail(@Path("id") String id, @Query("apiKey") String apiKey);
}
