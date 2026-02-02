/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package booking.bookingSystem.service;

import booking.bookingSystem.repository.UserRepository;
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
    
    
}
