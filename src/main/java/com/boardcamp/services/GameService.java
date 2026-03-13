package com.boardcamp.services;

import com.boardcamp.dtos.GameDTO;
import com.boardcamp.models.Game;
import com.boardcamp.repositories.GameRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class GameService {

    private final GameRepository repository;

    public GameService(GameRepository repository) {
        this.repository = repository;
    }

    public List<Game> findAll() {
        return repository.findAll();
    }

    public Game create(GameDTO dto) {

        repository.findByName(dto.getName())
                .ifPresent(game -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT);
                });

        Game game = new Game();

        game.setName(dto.getName());
        game.setImage(dto.getImage());
        game.setStockTotal(dto.getStockTotal());
        game.setPricePerDay(dto.getPricePerDay());

        return repository.save(game);
    }
}