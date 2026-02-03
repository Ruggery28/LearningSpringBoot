/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package booking.bookingSystem.service;

import booking.bookingSystem.model.User;
import booking.bookingSystem.repository.UserRepository;
import java.util.Optional;
import org.apache.catalina.startup.PasswdUserDatabase;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ruggery
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository; //this communicate with our UserRepository
    private final PasswordEncoder passwordEncoder;

    // This constructor tells Spring to "inject" these two tools automatically
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(User user) {
        //check if the email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered. Please login.");
        }

        //hash the raw password
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        //now, I need to update the user password
        user.setPassword(encodedPassword);

        //finally, save to the database all data
        userRepository.save(user);

    }

    // Method to find the user by email and return the object with full information
    public User findUserByEmail(String email) {
        // We reuse the repository method we already have
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //look for the user in the database by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        //return a Spring Security userDetails object
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles("USER") // Assign a default role
                .build();
    }

}
