/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package booking.bookingSystem.service;

import booking.bookingSystem.model.User;
import booking.bookingSystem.repository.UserRepository;
import java.util.Optional;
import org.apache.catalina.startup.PasswdUserDatabase;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ruggery
 */
@Service
public class UserService {
    
    private final UserRepository userRepository; //this communicate with our UserRepository
    private final PasswordEncoder passwordEncoder;
    
    // This constructor tells Spring to "inject" these two tools automatically
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public void registerUser(User user){
        //check if the email already exists
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new RuntimeException("Email already registered. Please login.");
        }
        
        //hash the raw password
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        
        //now, I need to update the user password
        user.setPassword(encodedPassword);
        
        //finally, save to the database all data
        userRepository.save(user);
        
    }
    
}
