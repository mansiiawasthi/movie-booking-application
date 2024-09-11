package com.wipro.moviebookingapplication.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.moviebookingapplication.entity.Movie;
import com.wipro.moviebookingapplication.exceptions.ResourceNotFoundException;
import com.wipro.moviebookingapplication.repository.MovieRepository;
import com.wipro.moviebookingapplication.service.MovieServices;

@Service
public class MovieServicesImpl implements MovieServices{
	
	@Autowired
    private MovieRepository movieRepository;

	@Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }
	
	@Override
	public Movie updateMovie(int id,Movie movie) {
		Optional<Movie> movieOpt = movieRepository.findById(id);
		if(movieOpt.isPresent()) {
			Movie curMovie = movieOpt.get();
			curMovie.setDescription(movie.getDescription());
			curMovie.setDuration_in_mins(movie.getDuration_in_mins());
			curMovie.setGenre(movie.getGenre());
			curMovie.setRating(movie.getRating());
			curMovie.setTitle(movie.getTitle());
//			curMovie.setShowtimes(movie.getShowtimes());
			
			return movieRepository.save(curMovie);
		}
			
		else
			throw new ResourceNotFoundException("No matching movie found with ID: "+id);
	}
	
	@Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

	@Override
    public Movie getMovieById(Integer id) {
		Optional<Movie> opt = movieRepository.findById(id);
		if(opt.isPresent())
			return opt.get();
		else
			throw new ResourceNotFoundException("No matching movie found with ID: "+id);
    }



	@Override
    public void deleteMovie(Integer id) {
		Optional<Movie> opt = movieRepository.findById(id);
		if(opt.isPresent()) {
			movieRepository.deleteById(id);
		}
		else
			throw new ResourceNotFoundException("No matching movie found with ID: "+id);     
    }
	
}
