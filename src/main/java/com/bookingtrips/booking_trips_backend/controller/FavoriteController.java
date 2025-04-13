package com.bookingtrips.booking_trips_backend.controller;

import com.bookingtrips.booking_trips_backend.dto.TripDto;
import com.bookingtrips.booking_trips_backend.entity.Favorite;
import com.bookingtrips.booking_trips_backend.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/favorites")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping("/{tripId}")
    public ResponseEntity<Object> addFavorite(@PathVariable UUID tripId) {
        Favorite favorite = favoriteService.addFavorite(tripId);
        return new ResponseEntity<>(favorite, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TripDto>> getUserFavorites() {
        return ResponseEntity.ok(favoriteService.getUserFavorites());
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity<Void> removeFavorite(@PathVariable UUID tripId) {
        favoriteService.removeFavorite(tripId);
        return ResponseEntity.noContent().build();
    }

}
