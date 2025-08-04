package com.dreamdev.apps.movierandomizer.repository;

import com.dreamdev.apps.movierandomizer.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Long> {
}
