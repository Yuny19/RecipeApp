package com.example.recipeapp.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bumptech.glide.Glide;
import com.example.recipeapp.BuildConfig;
import com.example.recipeapp.R;
import com.example.recipeapp.adapter.RecipeAdapter;
import com.example.recipeapp.apihelper.ApiService;
import com.example.recipeapp.apihelper.UtilsApi;
import com.example.recipeapp.model.DetailResult;
import com.example.recipeapp.model.RecipeResult;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailActivity extends AppCompatActivity {
    private static final String API_TOKEN = BuildConfig.API_KEY;
    private MutableLiveData<DetailResult> liveDetailRecipe = new MutableLiveData<>();


    ImageView ivImage;
    TextView tvTitle;

    public static final String EXTRA_ARTICLE = "details";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initContent();

        RecipeResult result = getIntent().getParcelableExtra(EXTRA_ARTICLE);

        //getId
        ApiService mApiService = UtilsApi.getApiService();
        Call<DetailResult> call = mApiService.getDetail(String.valueOf(result.getId()), API_TOKEN);
        call.enqueue(new Callback<DetailResult>() {
            @Override
            public void onResponse(Call<DetailResult> call, Response<DetailResult> response) {
                liveDetailRecipe.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DetailResult> call, Throwable t) {
                Log.e("onFailure", Objects.requireNonNull(t.getMessage()));
            }
        });

        //setValue

        String title = result.getTitle();


        String image = RecipeAdapter.BASE_URI + result.getImage();
        Glide.with(DetailActivity.this).load(image).into(ivImage);

//            String summary=result.getSummary();
        tvTitle.setText(title);

    }

    private void initContent() {
        ivImage = findViewById(R.id.iv_detail_recipe);
        tvTitle = findViewById(R.id.tv_title);
    }

    public LiveData<DetailResult> getRecipes() {
        return liveDetailRecipe;
    }
}
