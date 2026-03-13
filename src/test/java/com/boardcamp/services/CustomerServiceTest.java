package com.boardcamp.services;

import com.boardcamp.models.Customer;
import com.boardcamp.repositories.CustomerRepository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerService service;

    public CustomerServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateCustomerObject() {

        Customer customer = new Customer();
        customer.setName("Alice");
        customer.setCpf("12345678900");

        assertEquals("Alice", customer.getName());
        assertEquals("12345678900", customer.getCpf());
    }
}