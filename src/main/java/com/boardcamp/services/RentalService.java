package com.boardcamp.services;

import com.boardcamp.dtos.RentalDTO;
import com.boardcamp.models.Game;
import com.boardcamp.models.Customer;
import com.boardcamp.models.Rental;

import com.boardcamp.repositories.GameRepository;
import com.boardcamp.repositories.CustomerRepository;
import com.boardcamp.repositories.RentalRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final GameRepository gameRepository;
    private final CustomerRepository customerRepository;

    public RentalService(
            RentalRepository rentalRepository,
            GameRepository gameRepository,
            CustomerRepository customerRepository) {

        this.rentalRepository = rentalRepository;
        this.gameRepository = gameRepository;
        this.customerRepository = customerRepository;
    }

    public List<Rental> findAll() {
        return rentalRepository.findAll();
    }

    public Rental create(RentalDTO dto) {

        Game game = gameRepository.findById(dto.getGameId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        int rentedGames = rentalRepository
                .findByGameIdAndReturnDateIsNull(game.getId())
                .size();

        if (rentedGames >= game.getStockTotal()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Rental rental = new Rental();

        rental.setCustomer(customer);
        rental.setGame(game);
        rental.setDaysRented(dto.getDaysRented());
        rental.setRentDate(LocalDate.now());
        rental.setReturnDate(null);
        rental.setDelayFee(0);

        rental.setOriginalPrice(dto.getDaysRented() * game.getPricePerDay());

        return rentalRepository.save(rental);
    }

    public Rental finishRental(Long id) {

        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (rental.getReturnDate() != null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        LocalDate returnDate = LocalDate.now();
        rental.setReturnDate(returnDate);

        LocalDate expectedReturn = rental.getRentDate().plusDays(rental.getDaysRented());

        long delayDays = ChronoUnit.DAYS.between(expectedReturn, returnDate);

        if (delayDays > 0) {

            int delayFee = (int) delayDays * rental.getGame().getPricePerDay();

            rental.setDelayFee(delayFee);
        }

        return rentalRepository.save(rental);
    }

    public void deleteRental(Long id) {

        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (rental.getReturnDate() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        rentalRepository.deleteById(id);
    }
}