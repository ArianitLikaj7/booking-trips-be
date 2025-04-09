package com.bookingtrips.booking_trips_backend.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
    private Long id;
    private Long userId;
    private int seatNumber;
    private String reservedFor;
    private Long tripId;
}
