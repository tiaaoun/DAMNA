package com.example.damna;

import java.util.ArrayList;
import java.util.List;

public class DonationData {
    private static final List<BloodRequest> donations= new ArrayList<>();
    public static void addDonation(BloodRequest donation){
        donations.add(donation);
    }
    public static List<BloodRequest> getDonations(){
        return donations;
    }
}
