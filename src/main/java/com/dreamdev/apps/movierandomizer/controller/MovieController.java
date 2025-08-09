package com.dreamdev.apps.movierandomizer.controller;

import com.dreamdev.apps.movierandomizer.entity.Movie;
import com.dreamdev.apps.movierandomizer.repository.MovieRepo;
import com.dreamdev.apps.movierandomizer.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieRepo movieRepo;
    private final MovieService movieService;

    private ResponseEntity<String> movieNotFoundResponse() {
        return ResponseEntity.badRequest().body("Movie not found");
    }

    @GetMapping("/randomMovie")
    public ResponseEntity<?> getRandomMovie() {
        Movie foundRandomMovie = movieService.getRandomMovieService();
        if(foundRandomMovie!=null) {
            return ResponseEntity.ok(foundRandomMovie);
        }
        return movieNotFoundResponse();
    }

    @GetMapping("/randomMovieByGenre/{genre}")
    public ResponseEntity<?> getRandomMovieByGenre(@PathVariable String genre) {
        Movie foundRandomMovieByGenre = movieService.getRandomMovieByGenreService(genre);
        if(foundRandomMovieByGenre != null) {
            return ResponseEntity.ok(foundRandomMovieByGenre);
        }
        return movieNotFoundResponse();
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<?> getMovieById(@PathVariable Long movieId) {
        Movie foundMovie = movieService.getMovieByIdService(movieId);
        if(foundMovie != null) {
            return ResponseEntity.ok(foundMovie);
        }
        return movieNotFoundResponse();
    }


}
