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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @Column(nullable = false)
//    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @Column(unique = true, nullable = false)
    @NotNull(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    private String role;
    private String firstName;
    private String lastName;


//    @OneToMany(mappedBy = "user")
//    @JsonIgnore
//    private List<Booking> bookings;

}

