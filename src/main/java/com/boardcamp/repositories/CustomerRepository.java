package com.boardcamp.repositories;

import com.boardcamp.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCpf(String cpf);

}