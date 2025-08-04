package com.dreamdev.apps.movierandomizer.repository;

import com.dreamdev.apps.movierandomizer.entity.Genres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenresRepo extends JpaRepository<Genres, Long> {
}
