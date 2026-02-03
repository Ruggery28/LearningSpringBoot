/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package booking.bookingSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author Ruggery
 */
@Configuration
public class SecurityConfig {

    //method that will tell which page are public and which ones are private
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/register", "/css/**", "/js/**").permitAll() // Allow everyone here
                .anyRequest().authenticated() // Lock everything else
                )
                .formLogin(form -> form
                .loginPage("/login") // Tell Spring we will provide our own login page
                .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    //method to return the password encrypted 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
