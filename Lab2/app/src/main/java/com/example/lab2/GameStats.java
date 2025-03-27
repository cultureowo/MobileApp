package com.example.lab2;

public class GameStats {
    private int score;
    private long date;

    public GameStats(int score, long date) {
        this.score = score;
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public long getDate() {
        return date;
    }
}
