package com.example.lab2;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {
    private ListView statsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        statsListView = findViewById(R.id.statsListView);

        GameStatsDatabaseHelper dbHelper = new GameStatsDatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("stats", new String[] {"score", "date"}, null, null, null, null, "date DESC");

        ArrayList<String> statsList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        while (cursor.moveToNext()) {
            int score = cursor.getInt(cursor.getColumnIndex("score"));
            long date = cursor.getLong(cursor.getColumnIndex("date"));
            String formattedDate = sdf.format(new java.util.Date(date));
            statsList.add("Score: " + score + " Date: " + formattedDate);
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, statsList);
        statsListView.setAdapter(adapter);
    }
}
