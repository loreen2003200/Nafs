package com.example.nafs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class mood extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private List<CheckBox> moods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
//LoadSavedMoods();
        moods = new ArrayList<>();
        moods.add(findViewById(R.id.happy));
        moods.add(findViewById(R.id.confident));
        moods.add(findViewById(R.id.excited));
        moods.add(findViewById(R.id.sad));
        moods.add(findViewById(R.id.angry));
        moods.add(findViewById(R.id.nervous));
        moods.add(findViewById(R.id.normal));
        moods.add(findViewById(R.id.grateful));
        moods.add(findViewById(R.id.tired));
        moods.add(findViewById(R.id.loved));
        moods.add(findViewById(R.id.anxious));
        moods.add(findViewById(R.id.bored));
        moods.add(findViewById(R.id.pain));
        moods.add(findViewById(R.id.confused));

        ImageButton diary_button = findViewById(R.id.custom_button_diary);
        sharedPreferences = getSharedPreferences("Moods", Context.MODE_PRIVATE);

        RelativeLayout relativeLayout = findViewById(R.id.mainLayout);

        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();


        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start(); //run the animation

        diary_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCheckedMoods();
                GoToDiary();
            }

        });

        LoadSavedMoods();
    }

    //to save the checked moods
    private void saveCheckedMoods() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (CheckBox checkBox : moods) {
            String checkboxText = checkBox.getText().toString();
            boolean isChecked = checkBox.isChecked();
            // Save the text of checked checkboxes in SharedPreferences
            if (isChecked) {
                editor.putString(checkboxText, checkboxText);
            } else {
                editor.remove(checkboxText);
            }
        }

        editor.commit();
    }

    //loading the saved moods and setting the checked state of each checkbox
    private void LoadSavedMoods() {
        for (CheckBox checkBox : moods) {
            String checkboxText = checkBox.getText().toString();
            boolean isChecked = sharedPreferences.contains(checkboxText);
            checkBox.setChecked(isChecked);
        }
    }

    private void GoToDiary() {
        Intent intent = new Intent(mood.this, DiaryActivities.class);
        startActivity(intent);
        finish();
    }



}