package com.bookingtrips.booking_trips_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TripDto {
    private Long id;
    private String companyName;
    private String origin;
    private String destination;
    private int availableSeats;
    private int totalSeats;
    private String route;
    private Double price;
    private String title;
    private String description;
    private List<String> imageUrls;
    private String typeOfTrip;
    private LocalDateTime localDateTime;
}
