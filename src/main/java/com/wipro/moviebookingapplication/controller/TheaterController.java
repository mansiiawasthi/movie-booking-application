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

import com.wipro.moviebookingapplication.entity.Theater;
import com.wipro.moviebookingapplication.service.TheaterServices;

@RestController
@RequestMapping("api/v1/theaters")
@CrossOrigin(origins = "http://localhost:3000")
public class TheaterController {

	@Autowired
    private TheaterServices theaterService;


    @GetMapping
    public List<Theater> getAllTheaters() {
        return theaterService.getAllTheaters();
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<Theater> getTheaterById(@PathVariable Integer id) {
        Theater theater = theaterService.getTheaterById(id);
        return ResponseEntity.ok(theater);
    }

   
    @PostMapping
    public ResponseEntity<String> createTheater(@RequestBody Theater theater) {
    	Theater savedTheater = theaterService.saveTheater(theater);
        return new ResponseEntity<String>("Theater saved with ID: " + savedTheater.getTheater_id(),HttpStatus.CREATED);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTheater(@PathVariable Integer id, @RequestBody Theater theaterDetails) {
        Theater updatedTheater = theaterService.updateTheater(id, theaterDetails);
        return new ResponseEntity<String>("Theater with ID: " + id + " Updated!",HttpStatus.OK);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTheater(@PathVariable Integer id) {
        theaterService.deleteTheater(id);
        return new ResponseEntity<String>("Theater with ID: " + id + " Deleted!",HttpStatus.OK);
    }
    
}
