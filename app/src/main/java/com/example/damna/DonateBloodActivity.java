package com.example.damna;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DonateBloodActivity extends AppCompatActivity {

    EditText fullNameInput, phoneInput, bloodTypeInput, dateInput, centerNameInput;
    RadioGroup radioGroup;
    RadioButton radioCenter, radioContact;
    Button donateButton, findCentersButton;
    String recipientName, recipientPhone, recipientBloodType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_blood);

        ImageButton backBtn=findViewById(R.id.back_button);
        backBtn.setOnClickListener(v-> finish());

        fullNameInput=findViewById(R.id.full_name_input);
        phoneInput=findViewById(R.id.phone_input);
        bloodTypeInput= findViewById(R.id.blood_type_input);
        dateInput=findViewById(R.id.date_input);
        centerNameInput=findViewById(R.id.center_name_input);
        radioGroup=findViewById(R.id.radio_group);
        radioCenter=findViewById(R.id.radio_center);
        radioContact= findViewById(R.id.radio_contact);
        donateButton= findViewById(R.id.donate_button);
        findCentersButton= findViewById(R.id.find_centers_button);

        Intent intent= getIntent();
        recipientName=intent.getStringExtra("full_name");
        recipientPhone=intent.getStringExtra("phone");
        recipientBloodType=intent.getStringExtra("blood_type");
        radioGroup.setOnCheckedChangeListener((group, checkedId)-> {
            if (checkedId==R.id.radio_center){
                dateInput.setVisibility(View.VISIBLE);
                findCentersButton.setVisibility(View.VISIBLE);
                centerNameInput.setVisibility(View.VISIBLE);
            }
            else{
                dateInput.setVisibility(View.GONE);
                findCentersButton.setVisibility(View.GONE);
                centerNameInput.setVisibility(View.GONE);
            }
        });

        findCentersButton.setOnClickListener(v-> {
            Uri mapUri= Uri.parse("https://www.google.com/maps/search/blood+donation+center+near+me");
            Intent mapIntent= new Intent(Intent.ACTION_VIEW, mapUri);
            startActivity(mapIntent);
        });
        donateButton.setOnClickListener(v->{
            String donorName= fullNameInput.getText().toString().trim();
            String donorPhone= phoneInput.getText().toString().trim();
            String donorBloodType= bloodTypeInput.getText().toString().trim();
            String date= dateInput.getText().toString().trim();
            String centerName= centerNameInput.getText().toString().trim();

            if (donorName.isEmpty()||donorPhone.isEmpty()||donorBloodType.isEmpty()){
                Toast.makeText(this,"Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            String[] validTypes={"A+","A-","B+","B-","O+","O-","AB+","AB-"};
            boolean isValid=false;
            for (String type:validTypes){
                if (type.equalsIgnoreCase(donorBloodType)) {
                    isValid= true;
                    break;
                }
            }

            if (!isValid){
                bloodTypeInput.setError("Invalid blood type");
                return;
            }

            if (radioCenter.isChecked()){
                if (date.isEmpty()||centerName.isEmpty()){
                    Toast.makeText(this,"Please enter a date and center name", Toast.LENGTH_SHORT).show();
                    return;
                }

                BloodRequest donation= new BloodRequest(centerName, donorBloodType, donorName, donorPhone);
                DonationData.addDonation(donation);
                String message="Thank you "+donorName+"! Donation scheduled at "+centerName+" on "+date;
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                finish();
                return;
            }

            if (radioContact.isChecked()){
                if (recipientPhone==null||recipientPhone.isEmpty()) {
                    Toast.makeText(this,"No recipient phone found!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent dial= new Intent(Intent.ACTION_DIAL);
                dial.setData(Uri.parse("tel:" + recipientPhone));
                startActivity(dial);
                finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
