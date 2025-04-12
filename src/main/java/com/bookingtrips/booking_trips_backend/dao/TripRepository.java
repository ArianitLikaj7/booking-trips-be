package com.bookingtrips.booking_trips_backend.dao;

import com.bookingtrips.booking_trips_backend.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID> {

    List<Trip> findAllByCreatedBy(UUID userId);

    @Query("SELECT t FROM Trip t WHERE t.origin LIKE %:search% OR t.destination LIKE %:search% OR t.route LIKE %:search%")
    List<Trip> findTripsByProperties(String search);

    List<Trip> findTripsByPrice(Double price);

    @Query("SELECT t.availableSeats FROM Trip t WHERE t.id = :tripId AND t.availableSeats > 0")
    Long findAvailableSeats(Long tripId);
}
