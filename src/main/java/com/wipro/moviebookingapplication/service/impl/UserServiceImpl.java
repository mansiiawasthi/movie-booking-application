package com.wipro.moviebookingapplication.service.impl;

import com.wipro.moviebookingapplication.dto.UserDTO;
import com.wipro.moviebookingapplication.dto.UserLoginDTO;
import com.wipro.moviebookingapplication.dto.UserRegistrationDTO;
import com.wipro.moviebookingapplication.entity.Movie;
import com.wipro.moviebookingapplication.exceptions.ResourceNotFoundException;
import com.wipro.moviebookingapplication.entity.User;
import com.wipro.moviebookingapplication.repository.UserRepository;
import com.wipro.moviebookingapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserRegistrationDTO userRegistrationDTO) {
        // Check if the user with the same email already exists
        if (userRepository.findByEmail(userRegistrationDTO.getEmail()).isPresent()) {
            throw new RuntimeException("User with this email already exists");
        }

        // Create a new User entity from the UserRegistrationDTO
        User user = new User();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword())); // Encrypt the password
        user.setEmail(userRegistrationDTO.getEmail());
        user.setRole(userRegistrationDTO.getRole());
        user.setFirstName(userRegistrationDTO.getFirstName());
        user.setLastName(userRegistrationDTO.getLastName());

        return userRepository.save(user);
    }

    @Override
    public User loginUser(UserLoginDTO userLoginDTO) {
        Optional<User> userOptional = userRepository.findByUsernameOrEmail(userLoginDTO.getUsernameOrEmail(), userLoginDTO.getUsernameOrEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Check if the password matches
            if (passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
                return user; // Login successful
            } else {
                throw new RuntimeException("Invalid credentials");
            }
        } else {
            throw new RuntimeException("User not found with username or email: " + userLoginDTO.getUsernameOrEmail());
        }
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    public User updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        existingUser.setUsername(userDTO.getUsername());
        existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encrypt the new password
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setRole(userDTO.getRole());
        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());

        return userRepository.save(existingUser);
    }
//    @Override
//    public User updateUser(Long id, UserDTO userDTO) {
//        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Update fields conditionally
//        if (userDTO.getUsername() != null) {
//            existingUser.setUsername(userDTO.getUsername());
//        }
//        if (userDTO.getEmail() != null) {
//            existingUser.setEmail(userDTO.getEmail());
//        }
//        if (userDTO.getPassword() != null) {
//            existingUser.setPassword(userDTO.getPassword()); // Ensure you hash the password before saving
//        }
//
//        return userRepository.save(existingUser);
//    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Movie getMovieDetails(Long movieId) {
        return null;
    }

}
