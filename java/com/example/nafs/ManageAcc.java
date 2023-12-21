package com.example.nafs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ManageAcc extends AppCompatActivity {
    ImageView backarrow;
    TextView changepass;
    TextView changePass, saveButton;
    EditText usernameEditText, emailEditText, phoneEditText, dobEditText, bioEditText;

    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_acc);
        backarrow = findViewById(R.id.back);
        changepass = findViewById(R.id.change_pass);
        preferences = getSharedPreferences("UserInfo", 0);
        saveButton = findViewById(R.id.save);
        usernameEditText = findViewById(R.id.usernameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        phoneEditText = findViewById(R.id.numberEditText);
        dobEditText = findViewById(R.id.dobEditText);
        bioEditText = findViewById(R.id.bioEditText);

        // Set initial values in EditText fields
        usernameEditText.setText(preferences.getString("username", ""));
        emailEditText.setText(preferences.getString("Email", ""));
        phoneEditText.setText(preferences.getString("Phone", ""));
        dobEditText.setText(preferences.getString("DOB", ""));
        // Set bio text if available
        bioEditText.setText(preferences.getString("Bio", ""));

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ManageAcc.this, profile.class);
                startActivity(i);
            }
        });

        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ManageAcc.this, ChangePass.class);
                startActivity(i);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the edited information
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username", usernameEditText.getText().toString());
                editor.putString("Email", emailEditText.getText().toString());
                editor.putString("Phone", phoneEditText.getText().toString());
                editor.putString("DOB", dobEditText.getText().toString());
                editor.putString("Bio", bioEditText.getText().toString());
                editor.apply();

                // Inform the user that changes have been saved
                Toast.makeText(ManageAcc.this, "Changes saved successfully", Toast.LENGTH_SHORT).show();

                // Update the profile activity with the new information
                updateProfileActivity();
            }
        });
    }
    private void updateProfileActivity() {
        // You can use an Intent to refresh the ProfileActivity with the updated information
        Intent profileIntent = new Intent(ManageAcc.this, profile.class);
        startActivity(profileIntent);
        finish(); // Optional: Close the ManageAcc activity after updating the profile
    }
}