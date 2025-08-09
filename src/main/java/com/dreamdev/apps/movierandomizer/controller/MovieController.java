package com.dreamdev.apps.movierandomizer.controller;

import com.dreamdev.apps.movierandomizer.entity.Movie;
import com.dreamdev.apps.movierandomizer.repository.MovieRepo;
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

    @GetMapping("/randomMovie")
    public ResponseEntity<?> getRandomMovie() {
        // Logic to fetch a random movie from the database
        // For now, returning a placeholder response
        int totalMovies = (int) movieRepo.count();

//        get a random int between 1 and totalMovies
        long randomIndex = ((int) (Math.random() * totalMovies) + 1);

        Optional<Movie> foundMovieOptional = movieRepo.findById(randomIndex);
        if (foundMovieOptional.isPresent()) {
            return ResponseEntity.ok(foundMovieOptional.get());
        }
        log.error("Movie with ID {} not found", randomIndex);
        return ResponseEntity.badRequest().body("Movie not found");
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<?> getMovieById(@PathVariable Long movieId) {
        Optional<Movie> foundMovieOptional = movieRepo.findById(movieId);
        if (foundMovieOptional.isPresent()) {
            return ResponseEntity.ok(foundMovieOptional.get());
        }
        log.error("Movie with ID {} not found", movieId);
        return ResponseEntity.badRequest().body("Movie not found");
    }

}
