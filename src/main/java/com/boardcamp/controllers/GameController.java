package com.boardcamp.controllers;

import com.boardcamp.services.GameService;
import com.boardcamp.models.Game;
import com.boardcamp.dtos.GameDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @GetMapping
    public List<Game> getGames() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@Valid @RequestBody GameDTO dto) {
        Game game = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(game);
    }
}