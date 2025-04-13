package com.bookingtrips.booking_trips_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation extends BaseEntity {

    @Id
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UuidCreator.getTimeOrdered();
    }

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "seat_number")
    private int seatNumber;

    @Column(name = "reserved_for")
    private String reservedFor;

    @Column(name = "trip_id")
    private UUID tripId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "trip_id", insertable = false, updatable = false)
    @JsonBackReference
    private Trip trip;
}
