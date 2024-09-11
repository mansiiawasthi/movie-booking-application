package com.wipro.moviebookingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.moviebookingapplication.entity.Theater;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Integer> {

}
