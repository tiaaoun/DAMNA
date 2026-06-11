package com.example.damna;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RequestStatusActivity extends AppCompatActivity {
    RecyclerView requestsRecycler, donationsRecycler;
    TextView emptyMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_status);

        ImageButton backBtn=findViewById(R.id.back_button);
        backBtn.setOnClickListener(v -> finish());

        emptyMessage=findViewById(R.id.empty_message);
        requestsRecycler=findViewById(R.id.requests_recycler);
        donationsRecycler=findViewById(R.id.donations_recycler);
        List<BloodRequest> requests=RequestData.getRequests();
        List<BloodRequest> donations=DonationData.getDonations();

        if (requests.isEmpty()&&donations.isEmpty()) {
            emptyMessage.setVisibility(View.VISIBLE);
            requestsRecycler.setVisibility(View.GONE);
            donationsRecycler.setVisibility(View.GONE);
        }
        else {
            emptyMessage.setVisibility(View.GONE);
            if (!requests.isEmpty()){
                requestsRecycler.setLayoutManager(new LinearLayoutManager(this));
                SearchAdapter requestsAdapter=new SearchAdapter(requests, false);
                requestsRecycler.setAdapter(requestsAdapter);
            }
            else {
                requestsRecycler.setVisibility(View.GONE);
            }

            if (!donations.isEmpty()){
                donationsRecycler.setLayoutManager(new LinearLayoutManager(this));
                SearchAdapter donationsAdapter= new SearchAdapter(donations, false);
                donationsRecycler.setAdapter(donationsAdapter);
            }
            else {
                donationsRecycler.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
