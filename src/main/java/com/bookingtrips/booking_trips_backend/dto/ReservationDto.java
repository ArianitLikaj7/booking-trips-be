package com.bookingtrips.booking_trips_backend.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
    private UUID id;
    private UUID userId;
    private int seatNumber;
    private String reservedFor;
    private UUID tripId;
}
