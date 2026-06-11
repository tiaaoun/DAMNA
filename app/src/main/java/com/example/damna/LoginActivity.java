package com.example.damna;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText emailInput, passwordInput;
    Button loginBtn;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput=findViewById(R.id.email);
        passwordInput= findViewById(R.id.password);
        loginBtn= findViewById(R.id.login_button);

        sharedPreferences=getSharedPreferences("DAMNA_PREFS",MODE_PRIVATE);

        loginBtn.setOnClickListener(v-> {
            String email=emailInput.getText().toString();
            String password= passwordInput.getText().toString();

            if (email.equals("user@damna.com") && password.equals("1234")){
                sharedPreferences.edit().putString("USER_EMAIL", email).apply();
                startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                finish();
            }
            else {
                Toast.makeText(this,"Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
