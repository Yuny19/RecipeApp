package com.example.recipeapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.recipeapp.BuildConfig;
import com.example.recipeapp.apihelper.ApiService;
import com.example.recipeapp.apihelper.UtilsApi;
import com.example.recipeapp.model.RecipeRequest;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeViewModel extends ViewModel {
    private static final String API_TOKEN = BuildConfig.API_KEY;
    private MutableLiveData<RecipeRequest> liveDataNews = new MutableLiveData<>();

    public void setRecipe(String sources) {
        ApiService mApiService = UtilsApi.getApiService();
        Call<RecipeRequest> call = mApiService.getNewsList(sources, API_TOKEN);
        call.enqueue(new Callback<RecipeRequest>() {

            @Override
            public void onResponse(Call<RecipeRequest> call, Response<RecipeRequest> response) {
                Log.d("onResponse", response.message());
                liveDataNews.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RecipeRequest> call, Throwable t) {
                Log.e("onFailure", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    public LiveData<RecipeRequest> getNews() {
        return liveDataNews;
    }
}
