package com.wipro.moviebookingapplication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.moviebookingapplication.entity.Theater;
import com.wipro.moviebookingapplication.exceptions.ResourceNotFoundException;
import com.wipro.moviebookingapplication.repository.TheaterRepository;
import com.wipro.moviebookingapplication.service.TheaterServices;



@Service
public class TheaterServicesImpl implements TheaterServices {
	
	@Autowired
    private TheaterRepository theaterRepository;

	@Override
    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

	@Override
    public Theater getTheaterById(Integer id) {
        return theaterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Theater not found with id: " + id));
    }

	@Override
    public Theater saveTheater(Theater theater) {
        return theaterRepository.save(theater);
    }

	@Override
    public Theater updateTheater(Integer id, Theater theaterDetails) {
        Theater theater = getTheaterById(id);
        theater.setName(theaterDetails.getName());
        theater.setLocation(theaterDetails.getLocation());
        theater.setCapacity(theaterDetails.getCapacity());
//        theater.setShowtimes(theater.getShowtimes());
        return theaterRepository.save(theater);
    }

	@Override
    public void deleteTheater(Integer id) {
        Theater theater = getTheaterById(id);
        theaterRepository.delete(theater);
    }
}
