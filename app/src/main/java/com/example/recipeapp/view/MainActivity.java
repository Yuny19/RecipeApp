package com.example.recipeapp.view;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.R;
import com.example.recipeapp.adapter.RecipeAdapter;
import com.example.recipeapp.model.RecipeResult;
import com.example.recipeapp.viewmodel.RecipeViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<RecipeResult> results = new ArrayList<>();
    private RecipeAdapter adapter;
    private String baseUri;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_panpan);
        progressBar = findViewById(R.id.progressbar);

        RecipeViewModel recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        recipeViewModel.setRecipe();
        recipeViewModel.getRecipes().observe(this, recipeRequest -> {
            baseUri = recipeRequest.getBaseUriImage();
            List<RecipeResult> list = recipeRequest.getResult();
            results.addAll(list);
            adapter.notifyDataSetChanged();
        });
        setupRecyclerView();
        System.out.println(baseUri);

        new Thread(() -> {
            while (progressStatus < 100) {
                progressStatus += 1;
                // Update the progress bar and display the
                //current value in the text view
                handler.post(() -> progressBar.setProgress(progressStatus));
                try {
                    // Sleep for 200 milliseconds.
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void setupRecyclerView() {

        if (adapter == null) {
            adapter = new RecipeAdapter(MainActivity.this, baseUri, results);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}
