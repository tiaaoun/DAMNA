package com.example.damna;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import java.util.concurrent.Executor;

public class FingerprintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BiometricManager biometricManager=BiometricManager.from(this);
        int canAuthenticate=biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG);

        if (canAuthenticate!=BiometricManager.BIOMETRIC_SUCCESS) {
            Toast.makeText(this,"Fingerprint not supported or not set up", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        Executor executor=ContextCompat.getMainExecutor(this);
        BiometricPrompt biometricPrompt= new BiometricPrompt(FingerprintActivity.this, executor,
                new BiometricPrompt.AuthenticationCallback(){
                    @Override
                    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result){
                        super.onAuthenticationSucceeded(result);
                        String next=getIntent().getStringExtra("next");
                        if ("RequestStatus".equals(next)){
                            startActivity(new Intent(FingerprintActivity.this, RequestStatusActivity.class));
                        }
                        finish();
                    }

                    @Override
                    public void onAuthenticationError(int errorCode, @Nullable CharSequence errString){
                        super.onAuthenticationError(errorCode, errString);
                        Toast.makeText(getApplicationContext(),"Auth error: "+errString, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onAuthenticationFailed(){
                        super.onAuthenticationFailed();
                        Toast.makeText(getApplicationContext(),"Fingerprint not recognized", Toast.LENGTH_SHORT).show();
                    }
                });

        BiometricPrompt.PromptInfo promptInfo=new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Fingerprint Required")
                .setDescription("Use your fingerprint to continue")
                .setNegativeButtonText("Cancel")
                .build();

        biometricPrompt.authenticate(promptInfo);
    }
}
