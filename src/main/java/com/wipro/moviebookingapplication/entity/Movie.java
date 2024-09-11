package com.wipro.moviebookingapplication.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="MOVIES_TABLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movie_id;
    private String title;
    private String description;
    private String genre;
    private int duration_in_mins;
    private double rating;

//    @OneToMany(mappedBy = "movie")
////    @JsonIgnore
//    private List<Showtime> showtimes;
    
}
