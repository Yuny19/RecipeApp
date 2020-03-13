package com.example.recipeapp.view;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.recipeapp.R;
import com.example.recipeapp.adapter.RecipeAdapter;
import com.example.recipeapp.model.RecipeResult;
import com.example.recipeapp.viewmodel.RecipeViewModel;


public class DetailActivity extends AppCompatActivity {

    ImageView ivImage;
    TextView tvTitle, tvSummary;
    LinearLayout layout;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();


    public static final String EXTRA_ARTICLE = "details";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initContent();

        RecipeResult result = getIntent().getParcelableExtra(EXTRA_ARTICLE);

        RecipeViewModel recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        recipeViewModel.setDetailRecipe(String.valueOf(result.getId()));
        recipeViewModel.getDetailRecipes().observe(this, detailResult -> {
            tvSummary.setText(detailResult.getSummary());
            tvTitle.setText(detailResult.getTitle());
        });


        //setValue


        String image = RecipeAdapter.BASE_URI + result.getImage();
        Glide.with(DetailActivity.this).load(image).into(ivImage);

    }

    private void initContent() {
        ivImage = findViewById(R.id.iv_detail_recipe);
        tvTitle = findViewById(R.id.tv_title);
        tvSummary = findViewById(R.id.tv_content);

    }

}
