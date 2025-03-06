package com.example.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class EndGameActivity extends AppCompatActivity {
    private TextView finalScoreText;
    private Button restartButton, exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        finalScoreText = findViewById(R.id.finalScoreText);
        restartButton = findViewById(R.id.restartButton);
        exitButton = findViewById(R.id.exitButton);


        int score = getIntent().getIntExtra("score", 0);
        finalScoreText.setText("Ваш рахунок: " + score);


        restartButton.setOnClickListener(v -> {
            Intent restartIntent = new Intent(EndGameActivity.this, GameActivity.class);
            startActivity(restartIntent);
            finish();
        });


        exitButton.setOnClickListener(v -> finishAffinity());
    }
}
