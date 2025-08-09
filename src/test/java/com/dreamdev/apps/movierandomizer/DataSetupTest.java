package com.dreamdev.apps.movierandomizer;

import com.dreamdev.apps.movierandomizer.entity.Genre;
import com.dreamdev.apps.movierandomizer.entity.Movie;
import com.dreamdev.apps.movierandomizer.enums.MovieGenres;
import com.dreamdev.apps.movierandomizer.repository.GenreRepo;
import com.dreamdev.apps.movierandomizer.repository.MovieRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Profile("loadData")
@SpringBootTest
public class DataSetupTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private GenreRepo genreRepo;

    @Autowired
    private MovieRepo movieRepo;

    @Test
    public void GenreDataSetup() throws IOException {
        List<Genre> GenreList = genreRepo.findAll();
        if (GenreList.isEmpty()) {
            BufferedReader br = new BufferedReader(new FileReader("src/test/resources/genres.json"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            List<String> Genre = objectMapper.readValue(sb.toString(), new TypeReference<List<String>>() {
            });
            br.close();
            System.out.println(Genre);
            System.out.println(Genre.size());
            List<Genre> GenreToLoad = new ArrayList<>();
            for (String genre : Genre) {
                System.out.println(genre);
                Genre newGenre = new Genre();
                newGenre.setName(genre);
                GenreToLoad.add(newGenre);
            }

            genreRepo.saveAll(GenreToLoad);
            System.out.println("Genre loaded successfully: " + GenreToLoad.size());
        }
    }

    @Test
    public void MovieDataSetup() throws IOException {
        List<Movie> moviesList = movieRepo.findAll();
        if (moviesList.isEmpty()) {
            BufferedReader br = new BufferedReader(new FileReader("src/test/resources/movies.json"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            List<Movie> movies = objectMapper.readValue(sb.toString(), new TypeReference<List<Movie>>() {
            });
            System.out.println("Number of Movies to Add: " + movies.size());
            br.close();

            movies.stream().forEach(m -> {
                m.getGenres().stream().forEach(genre -> {
                    genre.setGenreId(MovieGenres.valueOf(genre.getName().toUpperCase()).getGenreId());
                });
            });

            movies.stream().forEach(m->{
               m.getGenres().stream().forEach(g->{
                   if(g==null){
                       System.out.println("Genre is null for movie: " + m.getTitle());
                   }

               });

//               movieRepo.save(m);
            });

            movieRepo.saveAll(movies);
            System.out.println("Movies loaded successfully: " + movies.size());
        }
    }
}
