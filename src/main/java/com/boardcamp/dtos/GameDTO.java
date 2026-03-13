package com.boardcamp.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameDTO {

    @NotBlank
    private String name;

    private String image;

    @Min(1)
    private Integer stockTotal;

    @Min(1)
    private Integer pricePerDay;
}
