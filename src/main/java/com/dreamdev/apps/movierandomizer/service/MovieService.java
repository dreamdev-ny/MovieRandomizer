package com.dreamdev.apps.movierandomizer.service;

import com.dreamdev.apps.movierandomizer.entity.Movie;
import com.dreamdev.apps.movierandomizer.enums.MovieGenres;
import com.dreamdev.apps.movierandomizer.repository.MovieRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MovieService {
    private final MovieRepo movieRepo;

    public Movie getRandomMovieService() {
        // Logic to fetch a random movie from the database
        // For now, returning a placeholder response
        int totalMovies = (int) movieRepo.count();

//        get a random int between 1 and totalMovies
        long randomIndex = ((int) (Math.random() * totalMovies) + 1);

        Optional<Movie> foundMovieOptional = movieRepo.findById(randomIndex);
        if (foundMovieOptional.isPresent()) {
            return foundMovieOptional.get();
        }

        log.error("Movie with ID {} not found", randomIndex);
        return null;
    }

    public Movie getMovieByIdService(Long movieId) {
        Optional<Movie> foundMovieOptional = movieRepo.findById(movieId);
        if (foundMovieOptional.isPresent()) {
            return foundMovieOptional.get();
        }
        log.error("Movie with ID {} not found", movieId);
        return null;
    }

    public Movie getRandomMovieByGenreService(String genre) {
        try {
            String generaUpperCase = MovieGenres.valueOf(genre.toUpperCase()).name();

            int counter = 0;
            while (counter < 100) {
                Movie randomMovie = getRandomMovieService();
                if (randomMovie != null && randomMovie.getGenres().stream()
                        .anyMatch(g -> g.getName().equalsIgnoreCase(generaUpperCase))) {
                    return randomMovie;
                }
                counter++;
            }
            return null;

        } catch (Exception e) {
            log.error("Invalid genre: {}", genre);
            return null;
        }
    }
}
