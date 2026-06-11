package com.example.damna;

public class BloodRequest {
    String hospitalName;
    String bloodType;
    String fullName;
    String phone;

    public BloodRequest(String hospitalName, String bloodType, String fullName, String phone) {
        this.hospitalName=hospitalName;
        this.bloodType=bloodType;
        this.fullName=fullName;
        this.phone=phone;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }
}
