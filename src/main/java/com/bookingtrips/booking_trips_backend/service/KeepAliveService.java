package com.bookingtrips.booking_trips_backend.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class KeepAliveService {

    @Scheduled(fixedRate = 300000) // çdo 5 minuta
    public void pingSelf() {
        try {
            URL url = new URL("https://booking-trips-be.onrender.com/api/v1/trips/all");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.getResponseCode();
            System.out.println("Keep-alive ping sent!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
