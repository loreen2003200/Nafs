package com.example.nafs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class history extends AppCompatActivity {
    TextView Goback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Goback = findViewById(R.id.button);
        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Get the selected date
                String date = year + "-" + (month + 1) + "-" + dayOfMonth;
                // Save the data to the calendar
                saveDataToCalendar(date);
            }
        });

        Goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(history.this, profile.class);
                startActivity(i);
            }
        });
    }

    private void saveDataToCalendar(String date) {
        // Get the calendar instance
        Calendar calendar = Calendar.getInstance();
        // Set the date of the calendar instance
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            calendar.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Set the event details
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, calendar.getTimeInMillis());
        values.put(CalendarContract.Events.DTEND, calendar.getTimeInMillis());
        values.put(CalendarContract.Events.TITLE, "Feelings Data");
        values.put(CalendarContract.Events.DESCRIPTION, "User's feelings data for " + date);
        values.put(CalendarContract.Events.CALENDAR_ID, 1);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());
        // Insert the event into the calendar
        ContentResolver cr = getContentResolver();
        Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
    }
}