package com.example.nafs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Map;

public class RegisterPage extends AppCompatActivity {

    TextView SignIn;
    Button signUp;
    TextInputEditText Email, Password, dob, mobile, confirmpassword, address, username;

    //  create shared preference to store user info locally
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        signUp = findViewById(R.id.sign_up);
        Email=findViewById(R.id.email);
        Password=findViewById(R.id.password);
        SignIn=findViewById(R.id.sign_in);
        dob=findViewById(R.id.dob);
        mobile=findViewById(R.id.mobile_number);
        confirmpassword=findViewById(R.id.confirm_pass);
        address=findViewById(R.id.address);
        username=findViewById(R.id.username);
        preferences = getSharedPreferences("UserInfo",0);


        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterPage.this, Login.class);
                startActivity(i);
                finish();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailValue,passwordValue,birthdayValue, numberValue, addressValue, confirm_passwordValue, usernameValue;
                emailValue = String.valueOf(Email.getText());
                passwordValue = String.valueOf(Password.getText());
                usernameValue = String.valueOf(username.getText());
                birthdayValue = String.valueOf(dob.getText());
                numberValue = String.valueOf(mobile.getText());
                addressValue = String.valueOf(address.getText());
                confirm_passwordValue = String.valueOf(confirmpassword.getText());

                if (usernameValue.length() > 1) {
                    // Check if the email already exists
                    if (isEmailExists(emailValue)) {
                        Toast.makeText(RegisterPage.this, "Email already exists, please login.", Toast.LENGTH_SHORT).show();
                    } else {
                        // Check if the password and confirm password match
                        if (passwordValue.equals(confirm_passwordValue)) {
                            // Passwords match, proceed with registration
                            // the user input values are retrieved from the TextInputEditText fields
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("username", usernameValue);
                            editor.putString("Email", emailValue);
                            editor.putString("Password", passwordValue);
                            editor.putString("Confirm_Password", confirm_passwordValue);
                            editor.putString("DOB", birthdayValue);
                            editor.putString("Phone", numberValue);
                            editor.putString("Address", addressValue);
                            editor.apply();
                            Toast.makeText(RegisterPage.this, "User Registered!", Toast.LENGTH_SHORT).show();

                            // Redirect to the login activity
                            Intent loginIntent = new Intent(RegisterPage.this, Login.class);
                            startActivity(loginIntent);
                            finish(); // Finish RegisterPage to prevent going back to it using the back button
                        } else {
                            // Passwords do not match
                            Toast.makeText(RegisterPage.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(RegisterPage.this, "Please fill the fields!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    // Function to check if the email already exists
    private boolean isEmailExists(String email) {
        // Retrieve the stored emails from SharedPreferences
        // iterates over all entries in the shared preferences and compares the provided email with stored emails
        Map<String, ?> allEntries = preferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            // Check if the entry key contains "Email" and the value matches the provided email
            if (entry.getKey().contains("Email") && entry.getValue().equals(email)) {
                // Email exists
                return true;
            }
        }
        // Email does not exist
        return false;
    }
}