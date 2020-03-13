package com.example.recipeapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeRequest {
    @SerializedName("recipe")
    @Expose
    private List<RecipeResult> result;


    public RecipeRequest() {
    }



    public List<RecipeResult> getResult() {
        return result;
    }

    public void setResult(List<RecipeResult> result) {
        this.result = result;
    }
}
