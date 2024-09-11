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
@Table(name="THEATERS_TABLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Theater {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long theater_id;
    private String name;
    private String location;
    private int capacity;

//    @OneToMany(mappedBy = "theater")
//    @JsonIgnore
//    private List<Showtime> showtimes;
    
}
