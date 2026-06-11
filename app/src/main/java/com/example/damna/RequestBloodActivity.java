package com.example.damna;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RequestBloodActivity extends AppCompatActivity {

    EditText fullNameInput, hospitalInput, bloodTypeInput, phoneInput;
    Button requestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_blood);

        fullNameInput=findViewById(R.id.full_name_input);
        hospitalInput= findViewById(R.id.hospital_input);
        bloodTypeInput= findViewById(R.id.blood_type_input);
        phoneInput=findViewById(R.id.phone_input);
        requestButton= findViewById(R.id.request_button);
        ImageButton backBtn= findViewById(R.id.back_button);
        backBtn.setOnClickListener(v -> finish());

        requestButton.setOnClickListener(v-> {
            String fullName= fullNameInput.getText().toString();
            String hospital= hospitalInput.getText().toString();
            String bloodType= bloodTypeInput.getText().toString();
            String phone= phoneInput.getText().toString();

            if (fullName.isEmpty()||hospital.isEmpty()||bloodType.isEmpty()||phone.isEmpty()) {
                Toast.makeText(this,"Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            String[] validTypes={"A+","A-","B+","B-","O+","O-","AB+","AB-"};
            boolean isValid= false;
            for (String type:validTypes) {
                if (type.equalsIgnoreCase(bloodType)) {
                    isValid= true;
                    break;
                }
            }

            if (!isValid){
                bloodTypeInput.setError("Invalid blood type");
                return;
            }
            BloodRequest request= new BloodRequest(hospital, bloodType, fullName, phone);
            RequestData.addRequest(request);
            Intent intent= new Intent(RequestBloodActivity.this, SearchActivity.class);
            intent.putExtra("hospital_name", hospital);
            intent.putExtra("blood_type", bloodType);
            intent.putExtra("full_name", fullName);
            intent.putExtra("phone", phone);
            startActivity(intent);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
