package com.example.damna;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<BloodRequest> fullList;
    private List<BloodRequest> fullListCopy;
    private boolean fromDonate;
    private Context context;

    public SearchAdapter(List<BloodRequest> list, boolean fromDonate) {
        this.fullList=list;
        this.fromDonate=fromDonate;
        this.fullListCopy=new ArrayList<>(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view=LayoutInflater.from(context).inflate(R.layout.item_request, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BloodRequest req= fullList.get(position);
        holder.fullName.setText("Name: "+req.getFullName());
        holder.phone.setText("Phone: "+req.getPhone());
        holder.hospitalName.setText("Hospital: "+req.getHospitalName());
        holder.bloodType.setText("Blood Type: "+req.getBloodType());

        holder.itemView.setOnClickListener(v-> {
            if (fromDonate){
                Intent intent=new Intent(context, DonateBloodActivity.class);
                intent.putExtra("full_name", req.getFullName());
                intent.putExtra("phone", req.getPhone());
                intent.putExtra("blood_type", req.getBloodType());
                context.startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount(){
        return fullList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView fullName, phone, hospitalName, bloodType;

        public ViewHolder(View itemView){
            super(itemView);
            fullName=itemView.findViewById(R.id.full_name);
            phone=itemView.findViewById(R.id.phone);
            hospitalName=itemView.findViewById(R.id.hospital_name);
            bloodType=itemView.findViewById(R.id.blood_type);
        }
    }

    public void filter(String query) {
        fullList.clear();
        if (query.isEmpty()) {
            fullList.addAll(fullListCopy);
        }
        else {
            for (BloodRequest item:fullListCopy) {
                if (item.getFullName().toLowerCase().contains(query.toLowerCase())) {
                    fullList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}