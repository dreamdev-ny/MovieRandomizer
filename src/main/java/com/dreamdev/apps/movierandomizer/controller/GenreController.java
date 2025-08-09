package com.dreamdev.apps.movierandomizer.controller;

import com.dreamdev.apps.movierandomizer.entity.Genre;
import com.dreamdev.apps.movierandomizer.repository.GenreRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/genres")
public class GenreController {
    private final GenreRepo genreRepo;

    @GetMapping("/all")
    public ResponseEntity<List<Genre>> getAllGenres() {
        return ResponseEntity.ok(genreRepo.findAll());
    }

}
