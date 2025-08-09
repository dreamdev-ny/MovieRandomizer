package com.dreamdev.apps.movierandomizer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.time.Instant;
import java.util.List;

@Data
@Entity(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("movie_id")
    private Long movieId;

    private String title;
    private String alias;
    private String description;
    private int year;

    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    List<Genre> genres;

    @JsonProperty("created_at")
    private Instant createdAt = Instant.now();

    @JsonProperty("updated_at")
    private Instant updatedAt = Instant.now();
}
