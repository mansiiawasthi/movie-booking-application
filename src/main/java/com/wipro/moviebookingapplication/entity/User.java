//package com.wipro.moviebookingapplication.entity;
//
//import java.util.List;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name="USERS_TABLE")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class User {
//
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long user_id;
//    private String username;
//    private String password;
//    private String email;
//    private String first_name;
//    private String last_name;
//    private String role;
//
//    @OneToMany(mappedBy = "user")
//    @JsonIgnore
//    private List<Booking> bookings;
//}

package com.wipro.moviebookingapplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "USERS_TABLE")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;
    private String role;
    private String firstName;
    private String lastName;

//    @OneToMany(mappedBy = "user")
//    @JsonIgnore
//    private List<Booking> bookings;

}

