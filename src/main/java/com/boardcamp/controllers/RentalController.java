package com.boardcamp.controllers;

import com.boardcamp.dtos.RentalDTO;
import com.boardcamp.models.Rental;
import com.boardcamp.services.RentalService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService service;

    public RentalController(RentalService service) {
        this.service = service;
    }

    @GetMapping
    public List<Rental> findAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Rental create(@RequestBody @Valid RentalDTO dto) {
        return service.create(dto);
    }

    @PostMapping("/{id}/return")
    public Rental finishRental(@PathVariable Long id) {
        return service.finishRental(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        service.deleteRental(id);
    }
}
