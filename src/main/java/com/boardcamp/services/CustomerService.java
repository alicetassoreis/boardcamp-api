package com.boardcamp.services;

import com.boardcamp.dtos.CustomerDTO;
import com.boardcamp.models.Customer;
import com.boardcamp.repositories.CustomerRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Customer findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Customer create(CustomerDTO dto) {

        repository.findByCpf(dto.getCpf())
                .ifPresent(c -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT);
                });

        Customer customer = new Customer();

        customer.setName(dto.getName());
        customer.setPhone(dto.getPhone());
        customer.setCpf(dto.getCpf());

        return repository.save(customer);
    }
}