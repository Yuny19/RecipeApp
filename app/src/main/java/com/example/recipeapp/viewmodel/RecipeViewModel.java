package com.example.recipeapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.recipeapp.BuildConfig;
import com.example.recipeapp.apihelper.ApiService;
import com.example.recipeapp.apihelper.UtilsApi;
import com.example.recipeapp.model.DetailResult;
import com.example.recipeapp.model.RecipeRequest;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeViewModel extends ViewModel {
    private static final String API_TOKEN = BuildConfig.API_KEY;

    private MutableLiveData<RecipeRequest> liveDataRecipe = new MutableLiveData<>();
    private MutableLiveData<DetailResult> liveDetailRecipe = new MutableLiveData<>();

    public void setRecipe() {
        ApiService mApiService = UtilsApi.getApiService();
        Call<RecipeRequest> call = mApiService.getNewsList(API_TOKEN);
        call.enqueue(new Callback<RecipeRequest>() {

            @Override
            public void onResponse(Call<RecipeRequest> call, Response<RecipeRequest> response) {

                Log.d("onResponse", (String.valueOf(response.body().isError())));
                liveDataRecipe.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RecipeRequest> call, Throwable t) {
                Log.e("onFailure", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    public LiveData<RecipeRequest> getRecipes() {
        return liveDataRecipe;
    }
    public LiveData<DetailResult> getDetailRecipes() {
        return liveDetailRecipe;
    }

    public void setDetailRecipe(String id){
        ApiService mApiService = UtilsApi.getApiService();
        Call<DetailResult> call = mApiService.getDetail(id, API_TOKEN);
        call.enqueue(new Callback<DetailResult>() {
            @Override
            public void onResponse(Call<DetailResult> call, Response<DetailResult> response) {
                Log.d("suummary", response.body().getSummary());
                liveDetailRecipe.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DetailResult> call, Throwable t) {
                Log.e("onFailure", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
