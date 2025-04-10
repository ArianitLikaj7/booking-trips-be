package com.bookingtrips.booking_trips_backend.dao;

import com.bookingtrips.booking_trips_backend.dto.ReservationDto;
import com.bookingtrips.booking_trips_backend.dto.ReservationDtoAndUserDto;
import com.bookingtrips.booking_trips_backend.dto.UserDto;
import com.bookingtrips.booking_trips_backend.dto.TripDto;
import com.bookingtrips.booking_trips_backend.entity.Reservation;
import com.bookingtrips.booking_trips_backend.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    boolean existsByUserIdAndTripId(Long userId,Long tripId);

    @Query("SELECT r FROM Reservation r WHERE r.tripId = :tripId AND r.userId = :userId")
    List<ReservationDto> findByTripId(Long userId, Long tripId);

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.userId = :userId")
    Long countReservation(Long userId);

    @Query("SELECT new com.bookingtrips.booking_trips_backend.dto.UserDto(" +
            "u.id, u.firstName, u.lastName, u.username, u.email, u.phoneNumber, u.imageUrlOfUser, u.role) " +
            "FROM User u " +
            "JOIN Reservation r ON u.id = r.userId " +
            "JOIN Trip t ON r.tripId = t.id " +
            "WHERE t.id = :tripId " +
            "AND t.createdBy = :userId")
    List<UserDto> getReservationsByTripId(Long userId, Long tripId);



    @Query("SELECT t FROM Trip t JOIN Reservation r ON t.id = r.tripId WHERE r.userId = :userId")
    List<Trip> findMyReservations(@Param("userId") Long userId);


    @Query("SELECT r FROM Reservation r JOIN FETCH r.trip t JOIN FETCH r.user u WHERE t.createdBy = :adminId")
    List<Reservation> findAllByTripCreatedBy(@Param("adminId") Long adminId);


}
