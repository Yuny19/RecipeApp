package com.example.recipeapp.apihelper;

public class UtilsApi {
    static final String BASE_URL="https://api.spoonacular.com/recipes/";

    public static ApiService getApiService(){
        return ApiClient.getClient().create(ApiService.class);
    }
}
