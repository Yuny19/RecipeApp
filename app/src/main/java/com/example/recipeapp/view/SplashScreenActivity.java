package com.example.recipeapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

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
        }, 1500);
    }
}
