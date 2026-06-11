package com.example.damna;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchAdapter adapter;
    ArrayList<BloodRequest> fullList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ImageButton backButton=findViewById(R.id.back_button);
        backButton.setOnClickListener(v->finish());

        boolean fromDonate=getIntent().getBooleanExtra("fromDonate",false);

        fullList.add(new BloodRequest("Saint George Hospital", "O+", "Maya Karim", "03111222"));
        fullList.add(new BloodRequest("AUBMC", "A−", "Ziad Tarek", "70123456"));
        fullList.add(new BloodRequest("LAU Medical", "B+", "Sara Nader", "03998877"));

        String hospital=getIntent().getStringExtra("hospital_name");
        String bloodType=getIntent().getStringExtra("blood_type");
        String fullName=getIntent().getStringExtra("full_name");
        String phone=getIntent().getStringExtra("phone");

        if (hospital!=null&&bloodType!=null&&fullName!=null&&phone!=null) {
            fullList.add(new BloodRequest(hospital, bloodType, fullName,phone));
        }

        adapter=new SearchAdapter(fullList, fromDonate);
        recyclerView.setAdapter(adapter);
        SearchView searchView=findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return false;
            }
        });

    }
}