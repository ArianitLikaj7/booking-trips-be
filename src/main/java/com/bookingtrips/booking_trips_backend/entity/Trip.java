package com.bookingtrips.booking_trips_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "trips")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trip extends BaseEntity {

    @Id
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UuidCreator.getTimeOrdered();
    }

    @Column(name = "created_by")
    private UUID createdBy;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column(name = "available_seats")
    private int availableSeats;

    @Column(name = "total_seats")
    private int totalSeats;

    @Column(name = "route")
    private String route;

    @Column(name = "price")
    private Double price;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "trip_images", joinColumns = @JoinColumn(name = "trip_id"))
    @Column(name = "image_url")
    private List<String> imageUrls;

    @Column(name = "local_date_time")
    private LocalDateTime localDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_trip")
    private TypeOfTrip typeOfTrip;

    @ManyToOne
    @JoinColumn(name = "created_by", insertable = false, updatable = false)
    @JsonBackReference
    private User user;
}
