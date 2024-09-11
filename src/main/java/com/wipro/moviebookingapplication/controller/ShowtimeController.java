package com.wipro.moviebookingapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.moviebookingapplication.entity.Showtime;
import com.wipro.moviebookingapplication.service.ShowtimeServices;


@RestController
@RequestMapping("api/v1/showtimes")
@CrossOrigin(origins = "http://localhost:3000")
public class ShowtimeController {

	@Autowired
	private ShowtimeServices showtimeService;

	@GetMapping
	public ResponseEntity<List<Showtime>> getAllShowtimes() {
		return ResponseEntity.ok(showtimeService.getAllShowtimes());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Showtime> getShowtimeById(@PathVariable Integer id) {
		Showtime showtime = showtimeService.getShowtimeById(id);
		return ResponseEntity.ok(showtime);
	}

	@PostMapping
	public ResponseEntity<String> createShowtime(@RequestBody Showtime showtime) {
		Showtime savedShowtime = showtimeService.saveShowtime(showtime);
		return new ResponseEntity<String>("Showtime saved with ID: " + savedShowtime.getShowtime_id(),HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateShowtime(@PathVariable Integer id, @RequestBody Showtime showtimeDetails) {
		Showtime updatedShowtime = showtimeService.updateShowtime(id, showtimeDetails);
		return new ResponseEntity<String>("Showtime with ID: " + updatedShowtime.getShowtime_id() + " Updated!",HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteShowtime(@PathVariable Integer id) {
		showtimeService.deleteShowtime(id);
		return new ResponseEntity<String>("Showtime with ID: " + id + " Deleted!",HttpStatus.OK);
	}
	
//	//to get all the showtimes of a movie by ID
//	@GetMapping("/movies/{movie_id}")
//	public ResponseEntity<List<Showtime>> getAllShowtimesByMovieId(@PathVariable Integer movie_id){
//		return ResponseEntity.ok(showtimeService.getAllShowtimesByMovieId(movie_id));
//	}
//	
//	//to get all the showtimes in a theater by ID
//	@GetMapping("/theaters/{theater_id}")
//	public ResponseEntity<List<Showtime>> getAllShowtimesByTheaterId(@PathVariable Integer theater_id){
//		return ResponseEntity.ok(showtimeService.getAllShowtimesByTheaterId(theater_id));
//	}

}
