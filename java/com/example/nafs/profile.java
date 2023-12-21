package com.example.nafs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class profile extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Retrieve user information from SharedPreferences
        SharedPreferences preferences = getSharedPreferences("UserInfo", 0);
        String username = preferences.getString("username", "");
        String email = preferences.getString("Email", "");
        String phone = preferences.getString("Phone", "");
        String address = preferences.getString("Address", "");
        String dob = preferences.getString("DOB", "");
        String bio = preferences.getString("Bio", "");

        // Initialize TextViews
        TextView usernameTextView = findViewById(R.id.usernameTextView);
        TextView emailTextView = findViewById(R.id.emailTextView);
        TextView phoneTextView = findViewById(R.id.phoneTextView);
        TextView addressTextView = findViewById(R.id.addressTextView);
        TextView dobTextView = findViewById(R.id.dobTextView);
        TextView bioTextView = findViewById(R.id.bioTextView);

        // Display user information
        usernameTextView.setText(username);
        emailTextView.setText(email);
        phoneTextView.setText(phone);
        addressTextView.setText(address);
        dobTextView.setText(dob);
        bioTextView.setText(bio);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Move this line here
        drawerToggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    Intent intent = new Intent(profile.this, MainActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.history) {
                    Intent intent = new Intent(profile.this, history.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.about) {
                    Intent intent = new Intent(profile.this, About_Us.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.logout) {
                    Toast.makeText(profile.this, "Logged out", Toast.LENGTH_SHORT).show();
                    logout();
                } else if (item.getItemId() == R.id.manage) {
                    Toast.makeText(profile.this, "Manage your Account Selected", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(profile.this, ManageAcc.class);
                    startActivity(intent);
                }
                return false;
            }
        });

    }
    private void logout() {
        // Navigate to the login activity
        Intent loginIntent = new Intent(profile.this, Login.class);
        startActivity(loginIntent);
        finish(); // Close the current activity to prevent going back to it using the back button
    }
    @Override
    public void onBackPressed(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}