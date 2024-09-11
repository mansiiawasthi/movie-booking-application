package com.wipro.moviebookingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.moviebookingapplication.entity.Showtime;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {

}
