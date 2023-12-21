package com.example.nafs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class ChangePass extends AppCompatActivity {
    TextInputEditText passwordEditText, confirmPasswordEditText;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        preferences = getSharedPreferences("UserInfo", 0);

        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirm_pass);

        findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });
    }

    private void changePassword() {
        String newPassword = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

        if (TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(ChangePass.this, "Please enter both password fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(ChangePass.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // update the password in SharedPreferences
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Password", newPassword);
        editor.apply();

        // Toast to inform the user that the password has been changed
        Toast.makeText(ChangePass.this, "Password changed successfully", Toast.LENGTH_SHORT).show();

        finish();
    }
}