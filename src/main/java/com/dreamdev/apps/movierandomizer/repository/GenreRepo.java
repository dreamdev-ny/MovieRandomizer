package com.dreamdev.apps.movierandomizer.repository;

import com.dreamdev.apps.movierandomizer.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepo extends JpaRepository<Genre, Integer> {
}
