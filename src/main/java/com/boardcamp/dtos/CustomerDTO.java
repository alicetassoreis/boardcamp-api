package com.boardcamp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

    @NotBlank
    private String name;

    @Pattern(regexp = "\\d{10,11}")
    private String phone;

    @Pattern(regexp = "\\d{11}")
    private String cpf;
}