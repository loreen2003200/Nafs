package com.example.nafs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class DiaryActivities extends AppCompatActivity {

    private EditText editTextDiaryEntry;
    private ImageButton buttonSave;
    private SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_activities);

        editTextDiaryEntry = findViewById(R.id.editTextDiaryEntry);
        buttonSave = findViewById(R.id.buttonSave);
        sharedPreferences = getSharedPreferences("diary_prefs", MODE_PRIVATE);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDiaryEntry();
            }
        });
    }

    private void saveDiaryEntry() {
        String currentEntries = sharedPreferences.getString("diary_entries", "");
        String newEntry = editTextDiaryEntry.getText().toString() + "\n\n" + currentEntries;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("diary_entries", newEntry);
        editor.apply();

        finish(); // Close this activity and return to the main activity

    }


}