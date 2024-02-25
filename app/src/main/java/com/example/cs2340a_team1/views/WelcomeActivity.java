package com.example.cs2340a_team1.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.cs2340a_team1.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Button startButton = findViewById(R.id.start);
        Button exitButton = findViewById(R.id.exit);

        startButton.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        exitButton.setOnClickListener(v -> {
            finish();
            System.exit(0);
        });
    }
}