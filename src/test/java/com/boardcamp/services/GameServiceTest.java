package com.boardcamp.services;

import com.boardcamp.models.Game;
import com.boardcamp.repositories.GameRepository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    @Mock
    private GameRepository repository;

    @InjectMocks
    private GameService service;

    public GameServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindGameById() {

        Game game = new Game();
        game.setId(1L);
        game.setName("Catan");

        when(repository.findById(1L)).thenReturn(Optional.of(game));

        Optional<Game> result = repository.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Catan", result.get().getName());
    }
}