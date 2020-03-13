package com.example.recipeapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeRequest {
    @SerializedName("results")
    @Expose
    private List<RecipeResult> result;

    @SerializedName("baseUri")
    @Expose
    private String baseUriImage;

    @SerializedName("isStale")
    @Expose
    private boolean error;


    public RecipeRequest() {
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getBaseUriImage() {
        return baseUriImage;
    }

    public void setBaseUriImage(String baseUriImage) {
        this.baseUriImage = baseUriImage;
    }

    public List<RecipeResult> getResult() {
        return result;
    }

    public void setResult(List<RecipeResult> result) {
        this.result = result;
    }
}
