package com.wipro.moviebookingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.moviebookingapplication.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
