package com.bookingtrips.booking_trips_backend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
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
    private List<MultipartFile> imageUrls;
    private String route;
    private LocalDateTime localDateTime;
}
