package com.boardcamp.controllers;

import com.boardcamp.dtos.CustomerDTO;
import com.boardcamp.models.Customer;
import com.boardcamp.services.CustomerService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Customer> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@RequestBody @Valid CustomerDTO dto) {
        return service.create(dto);
    }
}