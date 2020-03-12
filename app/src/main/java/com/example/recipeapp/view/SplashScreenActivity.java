package com.example.recipeapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.example.recipeapp.MainActivity;
import com.example.recipeapp.R;

public class SplashScreenActivity extends AppCompatActivity {

    LinearLayout layoutLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        layoutLogo = findViewById(R.id.line);

        // delay move to main activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent goToMain = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(goToMain);
                finish();
            }
        }, 1000);
    }
}