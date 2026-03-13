package com.boardcamp.services;

import com.boardcamp.models.Rental;
import com.boardcamp.models.Customer;
import com.boardcamp.models.Game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentalServiceTest {

    @Test
    void shouldCreateRentalObject() {

        Customer customer = new Customer();
        customer.setId(1L);

        Game game = new Game();
        game.setId(2L);

        Rental rental = new Rental();
        rental.setCustomer(customer);
        rental.setGame(game);
        rental.setDaysRented(3);

        assertEquals(1L, rental.getCustomer().getId());
        assertEquals(2L, rental.getGame().getId());
        assertEquals(3, rental.getDaysRented());
    }
}