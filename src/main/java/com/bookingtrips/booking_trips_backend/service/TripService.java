package com.bookingtrips.booking_trips_backend.service;

import com.bookingtrips.booking_trips_backend.dao.TripRepository;
import com.bookingtrips.booking_trips_backend.dto.TripDto;
import com.bookingtrips.booking_trips_backend.dto.request.TripRequest;
import com.bookingtrips.booking_trips_backend.dto.request.TripUpdateRequest;
import com.bookingtrips.booking_trips_backend.entity.Trip;
import com.bookingtrips.booking_trips_backend.exception.MismatchedInputException;
import com.bookingtrips.booking_trips_backend.exception.ResourceNotFoundException;
import com.bookingtrips.booking_trips_backend.mapper.TripMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository tripRepository;
    private final TripMapper tripMapper;
    private final AuthenticationService authenticationService;
    private final ImageUploadService imageUploadService;

    public TripDto create(TripRequest request) {
        Trip trip = tripMapper.toEntity(request);
        trip.setCreatedBy(authenticationService.getLoggedInUser().getUserId());

        if (request.getImageUrls() != null && !request.getImageUrls().isEmpty()) {
            List<String> imageUrls = request.getImageUrls().stream()
                    .map(image -> {
                        try {
                            return imageUploadService.uploadFile(image);
                        } catch (IOException e) {
                            throw new RuntimeException("Failed to upload image", e);
                        }
                    })
                    .collect(Collectors.toList());
            trip.setImageUrls(imageUrls);
        }

        Trip savedTrip = tripRepository.save(trip);
        return tripMapper.toDto(savedTrip);
    }

    public TripDto getById(UUID id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Trip with id %s not found", id)
                ));
        return tripMapper.toDto(trip);
    }

    public List<TripDto> getAll() {
        return tripRepository.findAll().stream()
                .map(tripMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<TripDto> getAllMyTrips() {
        return tripRepository.findAllByCreatedBy(authenticationService.getLoggedInUser().getUserId())
                .stream()
                .map(tripMapper::toDto)
                .collect(Collectors.toList());
    }

    public void saveEntity(Trip trip) {
        tripRepository.save(trip);
    }

    public Trip getEntityById(UUID id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Trip with id %s not found", id)
                ));
    }

    public TripDto update(UUID id, TripUpdateRequest request) {
        if (!id.equals(request.getId())) {
            throw new MismatchedInputException("Ids don't match");
        }
        Trip tripInDb = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Trip with id %s not found", id)));

        tripMapper.toEntity(request, tripInDb);

        if (request.getImageUrls() != null && !request.getImageUrls().isEmpty()) {
            List<String> uploadedUrls = request.getImageUrls().stream()
                    .map(image -> {
                        try {
                            return imageUploadService.uploadFile(image);
                        } catch (IOException e) {
                            throw new RuntimeException("Failed to upload image", e);
                        }
                    })
                    .collect(Collectors.toList());
            tripInDb.setImageUrls(uploadedUrls);
        }

        Trip updatedTrip = tripRepository.save(tripInDb);
        return tripMapper.toDto(updatedTrip);
    }


    public void deleteById(UUID id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Trip with id %s not found", id)
                ));

        if (trip.getImageUrls() != null && !trip.getImageUrls().isEmpty()) {
            for (String imageUrl : trip.getImageUrls()) {
                imageUploadService.deleteImageByUrl(imageUrl);
            }
        }

        tripRepository.delete(trip);
    }


    public List<TripDto> findTripsByProperties(String search) {
        return tripRepository.findTripsByProperties(search).stream()
                .map(tripMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<TripDto> findTripsByPrice(Double price) {
        return tripRepository.findTripsByPrice(price).stream()
                .map(tripMapper::toDto)
                .collect(Collectors.toList());
    }

    public Long findAvailableSeats(UUID tripId) {
        Long availableSeats = tripRepository.findAvailableSeats(tripId);
        if (availableSeats == null) {
            throw new ResourceNotFoundException(String.format("No available seats found for trip with id %s", tripId));
        }
        return availableSeats;
    }
}
