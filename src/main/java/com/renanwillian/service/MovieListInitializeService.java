package com.renanwillian.service;


import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.renanwillian.entity.Movie;
import com.renanwillian.entity.Producer;
import com.renanwillian.entity.Studio;
import com.renanwillian.repository.MovieRepository;
import com.renanwillian.repository.ProducerRepository;
import com.renanwillian.repository.StudioRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Transactional
@ApplicationScoped
public class MovieListInitializeService {

    private static final Logger LOGGER = Logger.getLogger("ImportMovieListService");
    private static final String WINNER = "yes";

    @Inject
    MovieRepository movieRepository;

    @Inject
    ProducerRepository producerRepository;

    @Inject
    StudioRepository studioRepository;

    public void importMovieList() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream movieList = classLoader.getResourceAsStream("movielist.csv");

        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(Objects.requireNonNull(movieList)))
                .withCSVParser(csvParser)
                .withSkipLines(1)
                .build()) {
            reader.forEach(this::importMovie);
        } catch (IOException e) {
            LOGGER.warning("Error importing csv " + e.getLocalizedMessage());
        }
    }

    private void importMovie(String[] movieData) {
        try {
            Movie movie = new Movie();
            movie.setYear(Integer.parseInt(movieData[0]));
            movie.setTitle(movieData[1]);
            List<String> studios = splitNames(movieData[2]);
            movie.setStudios(studios.stream().map(name -> studioRepository.findByName(name)
                                                                          .orElseGet(() -> new Studio(name)))
                                    .collect(Collectors.toList()));
            List<String> producers = splitNames(movieData[3]);
            movie.setProducers(producers.stream().map(name -> producerRepository.findByName(name)
                                                                                .orElseGet(() -> new Producer(name)))
                                        .collect(Collectors.toList()));
            movie.setWinner(WINNER.equals(movieData[4]));
            movieRepository.persist(movie);
        } catch (Exception e) {
            LOGGER.warning("Error importing line from csv " + e.getLocalizedMessage());
        }
    }

    private List<String> splitNames(String string) {
        return Arrays.asList(string.split(", | and "));
    }
}