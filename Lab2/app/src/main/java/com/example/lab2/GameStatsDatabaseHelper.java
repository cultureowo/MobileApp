package com.example.lab2;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

public class GameStatsDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "game_stats.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "stats";
    private static final String COLUMN_SCORE = "score";
    private static final String COLUMN_DATE = "date";

    public GameStatsDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SCORE + " INTEGER, " +
                COLUMN_DATE + " INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertGameStats(int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SCORE, score);
        values.put(COLUMN_DATE, System.currentTimeMillis());
        db.insert(TABLE_NAME, null, values);
    }
}
