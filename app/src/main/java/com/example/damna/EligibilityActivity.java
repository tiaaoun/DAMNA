package com.example.damna;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class EligibilityActivity extends AppCompatActivity {

    TextView rulesText;
    String url= "https://mocki.io/v1/72af00c5-0116-44e9-b482-f0ad5a9bdf78";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eligibility);
        rulesText= findViewById(R.id.rules_text);

        ImageButton backBtn= findViewById(R.id.back_button);
        backBtn.setOnClickListener(v -> finish());

        JsonObjectRequest jsonRequest= new JsonObjectRequest(
                Request.Method.GET, url, null,
                response-> {
                    try {
                        JSONArray rulesArray=response.getJSONArray("rules");
                        StringBuilder builder=new StringBuilder();
                        for (int i=0; i<rulesArray.length(); i++) {
                            builder.append(i+1).append(". ").append(rulesArray.getString(i)).append("\n\n");
                        }
                        rulesText.setText(builder.toString().trim());
                    }
                    catch (Exception e){
                        rulesText.setText("Error parsing rules.");
                    }
                },
                error->{
                    Log.e("VolleyError","Network error: "+error.toString());
                    if (error.networkResponse!=null) {
                        Log.e("VolleyError","Status code: "+error.networkResponse.statusCode);
                    }
                }
        );
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(jsonRequest);
    }
}
