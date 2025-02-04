package com.wipro.moviebookingapplication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.moviebookingapplication.entity.Theater;



@Service
public interface TheaterServices {

    void deleteTheater(Integer id);

    Theater updateTheater(Integer id, Theater theaterDetails);

    Theater saveTheater(Theater theater);

    Theater getTheaterById(Integer id);

    List<Theater> getAllTheaters();

}
