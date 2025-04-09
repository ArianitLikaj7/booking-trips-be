package com.bookingtrips.booking_trips_backend.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
}
