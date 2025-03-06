package com.example.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private TextView breedName, scoreText, timerText;
    private ImageView breedImage;
    private Button yesButton, noButton;
    private Random random;
    private int correctAnswers = 0;
    private String currentBreed;
    private int currentImageRes;
    private CountDownTimer countDownTimer;
    private long timeLeft = 60000; // 60 секунд

    private static final String[] BREEDS = {
            "Хаскі", "Вівчарка німецька", "Бульдог", "Пудель", "Чіхуахуа", "Кокер спаніель"
    };

    private static final int[] IMAGES = {
            R.drawable.husky, R.drawable.shepherd, R.drawable.bulldog,
            R.drawable.poodle, R.drawable.chihuahua, R.drawable.cocker
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        breedName = findViewById(R.id.breedName);
        breedImage = findViewById(R.id.breedImage);
        yesButton = findViewById(R.id.yesButton);
        noButton = findViewById(R.id.noButton);
        scoreText = findViewById(R.id.scoreText);
        timerText = findViewById(R.id.timerText);
        random = new Random();

        startGame();
        startTimer();
    }

    private void startGame() {
        generateNewRound();

        yesButton.setOnClickListener(v -> {
            checkAnswer(true);
            updateUI();
        });

        noButton.setOnClickListener(v -> {
            checkAnswer(false);
            updateUI();
        });
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                timerText.setText("Час: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                endGame();
            }
        }.start();
    }

    private void generateNewRound() {
        int correctIndex = random.nextInt(BREEDS.length);
        int imageIndex = random.nextInt(IMAGES.length);

        currentBreed = BREEDS[correctIndex];
        currentImageRes = IMAGES[imageIndex];

        breedName.setText(currentBreed);
        breedImage.setImageResource(currentImageRes);
    }

    private void checkAnswer(boolean isYes) {
        boolean correct = currentBreed.equals(getBreedNameFromImage(currentImageRes));

        if (correct == isYes) {
            correctAnswers++;
        } else {
            deductTime(5000); // Віднімаємо 5 секунд за неправильну відповідь
        }
    }

    private void deductTime(long millis) {
        countDownTimer.cancel();
        timeLeft -= millis;
        if (timeLeft <= 0) {
            endGame();
        } else {
            startTimer();
        }
    }

    private void updateUI() {
        scoreText.setText("Рахунок: " + correctAnswers);
        generateNewRound();
    }

    private void endGame() {
        Intent intent = new Intent(GameActivity.this, EndGameActivity.class);
        intent.putExtra("score", correctAnswers); // Передаємо рахунок правильно
        startActivity(intent);
        finish();
    }

    private String getBreedNameFromImage(int imageRes) {
        for (int i = 0; i < IMAGES.length; i++) {
            if (IMAGES[i] == imageRes) return BREEDS[i];
        }
        return "";
    }
}
