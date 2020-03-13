package com.example.recipeapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeResult implements Parcelable {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("readyInMinutes")
    @Expose
    private String readyCook;

    public RecipeResult() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getReadyCook() {
        return readyCook;
    }

    public void setReadyCook(String readyCook) {
        this.readyCook = readyCook;
    }

    protected RecipeResult(Parcel in) {
        id = in.readInt();
        title = in.readString();
        image = in.readString();
        readyCook = in.readString();
    }

    public static final Creator<RecipeResult> CREATOR = new Creator<RecipeResult>() {
        @Override
        public RecipeResult createFromParcel(Parcel in) {
            return new RecipeResult(in);
        }

        @Override
        public RecipeResult[] newArray(int size) {
            return new RecipeResult[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(image);
        dest.writeString(readyCook);
    }
}
