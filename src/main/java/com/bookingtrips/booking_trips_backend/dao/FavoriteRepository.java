package com.bookingtrips.booking_trips_backend.dao;

import com.bookingtrips.booking_trips_backend.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, UUID> {
    boolean existsByUserIdAndTripId(UUID userId, UUID tripId);
    List<Favorite> findByUserId(UUID userId);
    void deleteByUserIdAndTripId(UUID userId, UUID tripId);
}
