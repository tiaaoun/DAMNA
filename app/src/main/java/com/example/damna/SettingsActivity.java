package com.example.damna;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    Switch notificationSwitch;
    Button eligibilityButton, logoutButton;
    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        backButton=findViewById(R.id.back_button);
        notificationSwitch=findViewById(R.id.notification_switch);
        eligibilityButton=findViewById(R.id.eligibility_button);
        logoutButton=findViewById(R.id.logout_button);

        backButton.setOnClickListener(v-> finish());
        SharedPreferences prefs= getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        boolean notificationsEnabled=prefs.getBoolean("notifications_enabled", false);
        notificationSwitch.setChecked(notificationsEnabled);
        notificationSwitch.setOnCheckedChangeListener((buttonView, isChecked)-> {
            if (isChecked){
                Toast.makeText(this,"Notifications enabled", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this,"Notifications disabled", Toast.LENGTH_SHORT).show();
            }
            SharedPreferences.Editor editor=prefs.edit();
            editor.putBoolean("notifications_enabled", isChecked);
            editor.commit();
        });

        eligibilityButton.setOnClickListener(v-> {
            Intent intent=new Intent(SettingsActivity.this, EligibilityActivity.class);
            startActivity(intent);
        });

        logoutButton.setOnClickListener(v-> {
            Intent intent=new Intent(SettingsActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}
