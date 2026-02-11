/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package booking.bookingSystem.service;

import booking.bookingSystem.exceptions.EmailAlreadyRegisteredException;
import booking.bookingSystem.exceptions.PasswordNotMatchException;
import booking.bookingSystem.exceptions.UnderAgeUserException;
import booking.bookingSystem.exceptions.WeekPasswordException;
import booking.bookingSystem.model.User;
import booking.bookingSystem.repository.UserRepository;
import java.time.LocalDate;
import java.time.Period;
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

    public void checkAge(User user) {
        LocalDate today = LocalDate.now(); //save the date from today into a variable LocalDate
        //then, I calculate from the person's date to today's date and I will get a int
        Period period = Period.between(user.getBirthDate(), today);
        //I save it in a boolean to check if the real date is less then 18
        boolean checkAge = (period.getYears() < 18);

        //if the person is under 18, I stop the line and give it a error
        if (checkAge) {
            // This stops the process and sends a message back
            throw new UnderAgeUserException("User must be at least 18 years old to register.");
        }
    }

    public void registerUser(User user) {
        //if password and confirmPassword is not equal, throw this exception
        if(!user.getPassword().equals(user.getConfirmPassword())){
            throw new PasswordNotMatchException("Passwords do not match!");
        }
        
        //this will check if the password contains at least one special character
        String password = user.getPassword();
        if (!password.matches(".*[!@#$%^&*].*")){
            throw new WeekPasswordException("Password require at least one special character.");
        }
   
        //check if the email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyRegisteredException("Email already registered. Please login.");
        }

        //method we created to check the age
        checkAge(user);
           
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
