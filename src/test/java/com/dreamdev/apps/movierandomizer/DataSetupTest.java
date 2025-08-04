package com.dreamdev.apps.movierandomizer;

import com.dreamdev.apps.movierandomizer.entity.Genres;
import com.dreamdev.apps.movierandomizer.repository.GenresRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DataSetupTest {

    @Autowired
    private  ObjectMapper objectMapper;

    @Autowired
    private GenresRepo genresRepo;

    @Test
    public void GenresDataSetup() throws IOException {
        List<Genres> genresList = genresRepo.findAll();
        if(genresList.isEmpty()){
            BufferedReader br = new BufferedReader(new FileReader("src/test/resources/genres.json"));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
            List<String> genres = objectMapper.readValue(sb.toString(), new TypeReference<List<String>>() {});
            br.close();
            System.out.println(genres);
            System.out.println(genres.size());
            List<Genres> genresToLoad = new ArrayList<>();
            for (String genre : genres) {
                System.out.println(genre);
                Genres newGenre = new Genres();
                newGenre.setName(genre);
                genresToLoad.add(newGenre);
            }

            genresRepo.saveAll(genresToLoad);
            System.out.println("Genres loaded successfully: " + genresToLoad.size());
        }
    }
}
