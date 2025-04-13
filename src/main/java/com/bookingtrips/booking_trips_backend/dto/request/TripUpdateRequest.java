package com.bookingtrips.booking_trips_backend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TripUpdateRequest {
    @NotNull
    private UUID id;
    @NotNull private UUID userId;
    private String companyName;
    private String origin;
    private String destination;
    private int availableSeats;
    private int totalSeats;
    private String imageUrl;
    private String route;
    private LocalDateTime localDateTime;
}
