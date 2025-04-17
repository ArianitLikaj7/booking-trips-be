package com.bookingtrips.booking_trips_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BookingTripsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingTripsBackendApplication.class, args);
	}

}
