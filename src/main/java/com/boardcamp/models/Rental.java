package com.boardcamp.models;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "rentals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate rentDate;
    private Integer daysRented;
    private LocalDate returnDate;
    private Integer originalPrice;
    private Integer delayFee;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Game game;
}