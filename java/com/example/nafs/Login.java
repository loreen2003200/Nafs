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

public class Login extends AppCompatActivity {
    TextView SignUp;
    Button signIn;
    TextInputEditText editTextEmail, editTextPassword;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SignUp = findViewById(R.id.sign_up);
        editTextEmail=findViewById(R.id.email);
        editTextPassword=findViewById(R.id.password);
        signIn=findViewById(R.id.login);
        preferences = getSharedPreferences("UserInfo",0);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, RegisterPage.class);
                startActivity(i);
                finish();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password, registered_email, registered_pass;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                registered_email = preferences.getString("Email","");
                registered_pass = preferences.getString("Password","");

                // If the entered email and password match the stored info
                // the user is redirected to the Home_Page activity
                if(email.equals(registered_email) && password.equals(registered_pass)){
                    Intent i = new Intent(Login.this, MainActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(Login.this, "Invalid email or password!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}