package com.boardcamp.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "games")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String image;
    private Integer stockTotal;
    private Integer pricePerDay;
}