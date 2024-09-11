package com.wipro.moviebookingapplication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.moviebookingapplication.entity.Showtime;
import com.wipro.moviebookingapplication.exceptions.ResourceNotFoundException;
import com.wipro.moviebookingapplication.repository.ShowtimeRepository;
import com.wipro.moviebookingapplication.service.ShowtimeServices;



@Service
public class ShowtimeServicesImpl implements ShowtimeServices {
	
	@Autowired
    private ShowtimeRepository showtimeRepository;

	@Override
    public List<Showtime> getAllShowtimes() {
        return showtimeRepository.findAll();
    }

	@Override
    public Showtime getShowtimeById(Integer id) {
        return showtimeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Showtime not found with id: " + id));
    }

	@Override
    public Showtime saveShowtime(Showtime showtime) {
        return showtimeRepository.save(showtime);
    }

	@Override
    public Showtime updateShowtime(Integer id, Showtime showtimeDetails) {
        Showtime showtime = getShowtimeById(id);
        showtime.setMovie(showtimeDetails.getMovie());
        showtime.setTheater(showtimeDetails.getTheater());
        showtime.setShow_date(showtimeDetails.getShow_date());
        showtime.setShow_time(showtimeDetails.getShow_time());
//        showtime.setBookings(showtimeDetails.getBookings());
        return showtimeRepository.save(showtime);
    }

	@Override
    public void deleteShowtime(Integer id) {
        Showtime showtime = getShowtimeById(id);
        showtimeRepository.delete(showtime);
    }


}
