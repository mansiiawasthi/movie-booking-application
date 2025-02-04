package com.wipro.moviebookingapplication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.moviebookingapplication.entity.Movie;


@Service
public interface MovieServices {

    List<Movie> getAllMovies();

    Movie getMovieById(Integer id);

    Movie saveMovie(Movie movie);

    void deleteMovie(Integer id);

    Movie updateMovie(int id, Movie movie);

}
