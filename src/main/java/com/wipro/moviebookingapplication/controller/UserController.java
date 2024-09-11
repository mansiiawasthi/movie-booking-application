//package com.wipro.moviebookingapplication.controller;
//
//import com.wipro.moviebookingapplication.entity.User;
//import com.wipro.moviebookingapplication.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/auth") // Use a common base path for your APIs
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
////    @PostMapping("/register")
////    public ResponseEntity<String> signup(@RequestParam String username,
////                                         @RequestParam String password,
////                                         @RequestParam String email,
////                                         @RequestParam String firstName,
////                                         @RequestParam String lastName,
////                                         @RequestParam String role) {
////        User user = new User();
////        user.setUsername(username);
////        user.setPassword(password);
////        user.setEmail(email);
////        user.setFirst_name(firstName);
////        user.setLast_name(lastName);
////        user.setRole(role);
////        userService.saveUser(user);
////        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
////    }
//
//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(
//            @RequestParam String username,
//            @RequestParam String password,
//            @RequestParam String email,
//            @RequestParam String firstName,
//            @RequestParam String lastName,
//            @RequestParam String role) {
//
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setEmail(email);
//        user.setFirst_name(firstName);
//        user.setLast_name(lastName);
//        user.setRole(role);
//
//        userService.saveUser(user);
//        return ResponseEntity.ok("User registered successfully");
//    }
//
//
////    @PostMapping("/login")
////    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
////        // The actual authentication is handled by Spring Security, so you don't need to manually handle it
////        return new ResponseEntity<>("Login endpoint", HttpStatus.OK);
////    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest , BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            String errors = bindingResult.getAllErrors()
//                    .stream()
//                    .map(e -> e.getDefaultMessage())
//                    .collect(Collectors.joining(", "));
//            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
////            return new RedirectView("http://google.com");
//        }
//
//        try {
//            User user = userService.findByUsername(userService);
//            if(user.getRole().equalsIgnoreCase("Admin")){
//                return new ResponseEntity<>("admin-dashboard", HttpStatus.OK);
//            }
//            else{
//                return new ResponseEntity<>("user-dashboard", HttpStatus.OK);
//            }
//
////             return new RedirectView("http://localhost:3000/");
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
////            return new RedirectView("http://google.com");
//        }
//    }
//
//
//    @GetMapping("/logout")
//    public ResponseEntity<String> logout() {
//        // Logging out will invalidate the session in Spring Security
//        return new ResponseEntity<>("Logout successful", HttpStatus.OK);
//    }
//}


package com.wipro.moviebookingapplication.controller;

import com.wipro.moviebookingapplication.entity.Movie;
import com.wipro.moviebookingapplication.dto.UserDTO;
import com.wipro.moviebookingapplication.dto.UserLoginDTO;
import com.wipro.moviebookingapplication.dto.UserRegistrationDTO;
import com.wipro.moviebookingapplication.entity.User;
import com.wipro.moviebookingapplication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}/movies/{movieId}")
    public ResponseEntity<Movie> getMovieDetails(@PathVariable Long userId, @PathVariable Long movieId) {
        return ResponseEntity.ok(userService.getMovieDetails(movieId));
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Collect validation error messages and return a bad request response
            String errors = bindingResult.getAllErrors()
                    .stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        try {
            userService.registerUser(userRegistrationDTO);
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@Valid @RequestBody UserLoginDTO userLoginDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getAllErrors()
                    .stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            Map<String, String> response = new HashMap<>();
            response.put("error", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            User user = userService.loginUser(userLoginDTO);
            Map<String, String> response = new HashMap<>();
            if (user.getRole().equalsIgnoreCase("Admin")) {
                response.put("redirectUrl", "/admin-dashboard");
            } else {
                response.put("redirectUrl", "/user-dashboard");
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginDTO userLoginDTO, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            String errors = bindingResult.getAllErrors()
//                    .stream()
//                    .map(e -> e.getDefaultMessage())
//                    .collect(Collectors.joining(", "));
//            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
////            return new RedirectView("http://google.com");
//        }
//
//        try {
//            User user = userService.loginUser(userLoginDTO);
//            if(user.getRole().equalsIgnoreCase("Admin")){
//                return new ResponseEntity<>("admin-dashboard", HttpStatus.OK);
//            }
//            else{
//                return new ResponseEntity<>("user-dashboard", HttpStatus.OK);
//            }
//
////             return new RedirectView("http://localhost:3000/");
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
////            return new RedirectView("http://google.com");
//        }
//    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) {
        User updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return ResponseEntity.noContent().build();
//    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        System.out.println(users);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(users);
    }
}
