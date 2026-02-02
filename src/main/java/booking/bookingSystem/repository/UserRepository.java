/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package booking.bookingSystem.repository;

import booking.bookingSystem.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ruggery
 */
public interface UserRepository extends JpaRepository<User, Integer>{
    
    /*We use Optional, because the user might not have that email,
    so we avoid NullPointerException, then we name it as findBy(name of variable). 
    This will basically communicate with sql to look for the email column
    and run the query: SELECT * FROM users WHERE email = ?*/
    Optional<User> findByEmail(String email);
    
}
