package com.bookingtrips.booking_trips_backend.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrentLoggedInUserDto {
        private UUID userId;
        private String username;
        private String firstName;
        private String lastName;
        private String email;
        private String role;

}
