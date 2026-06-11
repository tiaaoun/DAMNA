package com.example.damna;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        LinearLayout donateBtn=findViewById(R.id.donate_btn);
        LinearLayout findBloodBtn=findViewById(R.id.find_blood_btn);
        Button nextBtn=findViewById(R.id.next_btn);

        ImageButton settingsButton=findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(v-> {
            Intent intent= new Intent(DashboardActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        donateBtn.setOnClickListener(v-> {
            Intent intent=new Intent(DashboardActivity.this, SearchActivity.class);
            intent.putExtra("fromDonate", true);
            startActivity(intent);
        });

        findBloodBtn.setOnClickListener(v-> {
            Intent intent=new Intent(DashboardActivity.this, RequestBloodActivity.class);
            startActivity(intent);
        });

        nextBtn.setOnClickListener(v-> {
            Toast.makeText(this, "Next clicked", Toast.LENGTH_SHORT).show();
        });

        BottomNavigationView bottomNav=findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_home);

        bottomNav.setOnItemSelectedListener(item-> {
            int itemId= item.getItemId();
            if (itemId==R.id.nav_home){
                return true;
            }
            else if (itemId==R.id.nav_search){
                startActivity(new Intent(this, SearchActivity.class));
                return true;
            }
            else if (itemId==R.id.nav_notifications){
            Intent intent= new Intent(DashboardActivity.this, FingerprintActivity.class);
            intent.putExtra("next","RequestStatus");
            startActivity(intent);
            return true;
        }

        return false;
        });

    }
}
