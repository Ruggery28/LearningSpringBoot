/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package booking.bookingSystem.controller;

import booking.bookingSystem.model.User;
import booking.bookingSystem.service.UserService;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Ruggery
 */
@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    //this method will sent the register HTML file when the user view the /register page
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; //the HTML file needs to match this name
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        //if there is any error we need to send the page back with the error message to the user
        if (result.hasErrors()) {
            return "register";
        }

        //if the registration went trhough, we will ask for the method to register and send the data to it.
        try {
            userService.registerUser(user);
            return "redirect:/login";
        } catch (RuntimeException e) {
            // If the email exists, we send the error message back to the form
            model.addAttribute("registrationError", e.getMessage());
            return "register";
        }

    }

}
