package com.wipro.moviebookingapplication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.moviebookingapplication.entity.Showtime;



@Service
public interface ShowtimeServices {

	void deleteShowtime(Integer id);

	Showtime updateShowtime(Integer id, Showtime showtimeDetails);

	Showtime saveShowtime(Showtime showtime);

	Showtime getShowtimeById(Integer id);

	List<Showtime> getAllShowtimes();

}
