package com.example.damna;

import java.util.ArrayList;
import java.util.List;

public class RequestData {
    private static final List<BloodRequest> requests= new ArrayList<>();

    public static void addRequest(BloodRequest r) {
        requests.add(r);
    }

    public static List<BloodRequest> getRequests() {
        return requests;
    }
}
