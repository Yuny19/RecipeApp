package com.example.recipeapp.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.recipeapp.R;
import com.example.recipeapp.model.DetailResult;

public class DetailActivity extends AppCompatActivity {
    ImageView ivImage;
    TextView tvTitle;

    public static final String EXTRA_ARTICLE = "extra_article";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initContent();

        DetailResult result=getIntent().getParcelableExtra(EXTRA_ARTICLE);
        if (result!=null){


            String title=result.getTitle();

//            Glide.with(DetailActivity.this).load(image[1]).into(ivImage);
            tvTitle.setText(title);
        }
    }

    private void initContent() {
        ivImage=findViewById(R.id.iv_poster);
        tvTitle=findViewById(R.id.tv_title);
    }
}
