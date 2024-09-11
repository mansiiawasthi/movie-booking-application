//package com.wipro.moviebookingapplication.service;
//
//import com.wipro.moviebookingapplication.entity.User;
//
//import java.util.Optional;
//
//public interface UserService {
//    void saveUser(User user);
//    User findByUsername(Object username);
//
//    Optional<User> findByUsername(String username);
//}


package com.wipro.moviebookingapplication.service;

import com.wipro.moviebookingapplication.entity.Movie;
import com.wipro.moviebookingapplication.dto.UserDTO;
import com.wipro.moviebookingapplication.dto.UserLoginDTO;
import com.wipro.moviebookingapplication.dto.UserRegistrationDTO;
import com.wipro.moviebookingapplication.entity.User;

import java.util.List;

public interface UserService {
    User registerUser(UserRegistrationDTO userRegistrationDTO);
    User loginUser(UserLoginDTO userLoginDTO);
    User getUserByUsername(String username);
    User getUserById(Long id);
    User updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    Movie getMovieDetails(Long movieId);
    List<User> getAllUsers();
}
