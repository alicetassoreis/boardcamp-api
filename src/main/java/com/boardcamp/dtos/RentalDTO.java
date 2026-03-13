package com.boardcamp.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalDTO {

    @NotNull
    private Long customerId;

    @NotNull
    private Long gameId;

    @NotNull
    @Min(1)
    private Integer daysRented;
}