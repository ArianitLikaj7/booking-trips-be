package com.bookingtrips.booking_trips_backend.service;

import com.bookingtrips.booking_trips_backend.dao.ReservationRepository;
import com.bookingtrips.booking_trips_backend.dto.ReservationDto;
import com.bookingtrips.booking_trips_backend.dto.ReservationDtoAndUserDto;
import com.bookingtrips.booking_trips_backend.dto.TripDto;
import com.bookingtrips.booking_trips_backend.dto.UserDto;
import com.bookingtrips.booking_trips_backend.dto.request.ReservationRequest;
import com.bookingtrips.booking_trips_backend.dto.request.ReservationUpdateRequest;
import com.bookingtrips.booking_trips_backend.entity.Reservation;
import com.bookingtrips.booking_trips_backend.entity.Trip;
import com.bookingtrips.booking_trips_backend.entity.User;
import com.bookingtrips.booking_trips_backend.exception.IlegalNumberOfSeatsException;
import com.bookingtrips.booking_trips_backend.exception.MismatchedInputException;
import com.bookingtrips.booking_trips_backend.exception.ReservationAlreadyExists;
import com.bookingtrips.booking_trips_backend.exception.ResourceNotFoundException;
import com.bookingtrips.booking_trips_backend.mapper.ReservationMapper;
import com.bookingtrips.booking_trips_backend.mapper.TripMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final AuthenticationService authenticationService;
    private final TripService tripService;
    private final TripMapper tripMapper;

    @Transactional
    public ReservationDto create(ReservationRequest request) {
        Long userId = authenticationService.getLoggedInUser().getUserId();

        if (reservationRepository.existsByUserIdAndTripId(userId, request.getTripId())) {
            throw new ReservationAlreadyExists("Reservation for this trip already exists.");
        }

        validateSeatAvailability(request.getTripId(), request.getSeatNumber());

        Reservation reservation = reservationMapper.toEntity(request);
        reservation.setUserId(userId);
        mapTripToReservation(request, reservation);

        updateAvailableSeats(request.getTripId(), request.getSeatNumber());

        Reservation reservationInDb = reservationRepository.save(reservation);
        return reservationMapper.toDto(reservationInDb);
    }

    public ReservationDto getById(Long id) {
        Reservation reservationInDb = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation with id %s not found".formatted(id)));
        return reservationMapper.toDto(reservationInDb);
    }

    public List<ReservationDtoAndUserDto> getAllReservationsForAdmin() {
        Long adminId = authenticationService.getLoggedInUser().getUserId();
        List<Reservation> reservations = reservationRepository.findAllByTripCreatedBy(adminId);

        return reservations.stream().map(r -> {
            Trip trip = r.getTrip();
            User user = r.getUser();

            return new ReservationDtoAndUserDto(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getPhoneNumber(),
                    trip.getId(),
                    trip.getCompanyName(),
                    trip.getOrigin(),
                    trip.getDestination(),
                    trip.getAvailableSeats(),
                    trip.getTotalSeats(),
                    trip.getRoute(),
                    trip.getPrice(),
                    trip.getTitle(),
                    trip.getDescription(),
                    trip.getImageUrls(),
                    r.getReservedFor()
            );
        }).collect(Collectors.toList());
    }


    public List<TripDto> myReservation() {
        Long userId = authenticationService.getLoggedInUser().getUserId();
        List<Trip> trips = reservationRepository.findMyReservations(userId);

        if (trips.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("No reservations found for user with id: %s", userId)
            );
        }
        return trips.stream()
                .map(tripMapper::toDto)
                .collect(Collectors.toList());
    }


    public List<UserDto> getByTripIdAndUserId(Long tripId) {
        Long userId = authenticationService.getLoggedInUser().getUserId();
        return reservationRepository.getReservationsByTripId(userId, tripId);
    }

    public Long countReservation() {
        Long userId = authenticationService.getLoggedInUser().getUserId();
        return reservationRepository.countReservation(userId);
    }

    public ReservationDto update(Long id, ReservationUpdateRequest request) {
        if (!id.equals(request.getId())) {
            throw new MismatchedInputException("IDs don't match.");
        }

        Reservation reservationInDb = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation with id %s not found".formatted(id)));

        reservationMapper.toEntity(request, reservationInDb);
        return reservationMapper.toDto(reservationRepository.save(reservationInDb));
    }

    public void delete(Long id) {
        if (!reservationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reservation with id %s not found".formatted(id));
        }
        reservationRepository.deleteById(id);
    }

    private void updateAvailableSeats(Long tripId, int seatNumber) {
        Trip trip = tripService.getEntityById(tripId);
        int updatedSeats = trip.getAvailableSeats() - seatNumber;

        if (updatedSeats < 0) {
            throw new IlegalNumberOfSeatsException("Not enough available seats.");
        }

        trip.setAvailableSeats(updatedSeats);
        tripService.saveEntity(trip);
    }

    private void validateSeatAvailability(Long tripId, int seatNumber) {
        TripDto tripDto = tripService.getById(tripId);
        if (seatNumber > tripDto.getAvailableSeats() || seatNumber < 1) {
            throw new IlegalNumberOfSeatsException(
                    "Seat number %d is not available. Available: %d".formatted(seatNumber, tripDto.getAvailableSeats()));
        }
    }

    private void mapTripToReservation(ReservationRequest request, Reservation reservation) {
        reservation.setTripId(request.getTripId());
    }
}
