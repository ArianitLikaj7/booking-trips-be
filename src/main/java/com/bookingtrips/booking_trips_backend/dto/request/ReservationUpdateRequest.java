package com.bookingtrips.booking_trips_backend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationUpdateRequest {
    @NotNull private UUID id;
    @NotNull private UUID tripId;
    private int  seatNumber;
}
