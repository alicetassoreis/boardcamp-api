package com.boardcamp.controllers;

import com.boardcamp.services.GameService;
import com.boardcamp.models.Game;

import org.springframework.web.bind.annotation.*;

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
}