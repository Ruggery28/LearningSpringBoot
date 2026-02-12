/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package booking.bookingSystem.service;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ruggery
 */
@Service
public class EmailService {
    
    private final JavaMailSender javaMailSender; 

    //constructor injection
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    
    //method to send the email to the user
    public void sendWelcomeEmail(String name, String email){
        //import the class and create a instance for SimpleMailMessage
        SimpleMailMessage message = new SimpleMailMessage();
        //create the message, and set the subject, text and to who
        String messageEmail = "Hello, " + name + ". Welcome to the Application!";
        message.setSubject("Register completed successfully!");
        message.setText(messageEmail);
        message.setTo(email);
        
        //finally use the method to send the message to the email
        javaMailSender.send(message);
        
        System.out.println("Email sent successfully to " + email);
    }
    
    
    
}
