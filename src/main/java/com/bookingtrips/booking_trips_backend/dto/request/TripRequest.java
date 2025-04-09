package com.bookingtrips.booking_trips_backend.dto.request;

import com.bookingtrips.booking_trips_backend.entity.TypeOfTrip;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class TripRequest {
    private String origin;
    private String companyName;
    private String destination;
    private int availableSeats;
    private int totalSeats;
    private String route;
    private Double price;
    private String title;
    private String description;
    private TypeOfTrip typeOfTrip;
    private LocalDateTime localDateTime;
    private List<MultipartFile> images;
}
