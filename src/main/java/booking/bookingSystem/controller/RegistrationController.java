/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package booking.bookingSystem.controller;

import booking.bookingSystem.exceptions.EmailAlreadyRegisteredException;
import booking.bookingSystem.exceptions.PasswordNotMatchException;
import booking.bookingSystem.exceptions.UnderAgeUserException;
import booking.bookingSystem.exceptions.WeekPasswordException;
import booking.bookingSystem.model.User;
import booking.bookingSystem.service.UserService;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.Authentication;

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

    //method to check erros while registering, and requesting the method register from userService
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        //if there is any error we need to send the page back with the error message to the user
        if (result.hasErrors()) {
            return "register";
        }

        //if the registration went trhough, we will ask for the method to register and send the data to it.
        try {
            userService.registerUser(user);
            return "redirect:/login?success";
        } catch (EmailAlreadyRegisteredException e) {
            //If the email exists, we send the error message back to the form
            model.addAttribute("registrationError", e.getMessage()); //use model because we need to search inside the data first
            return "register";
        } catch (WeekPasswordException e) {
            //This attaches the error speciafically to the password formart
            result.rejectValue("password", "error.user", e.getMessage());
            return "register";
        } catch (PasswordNotMatchException e) {
            // This attaches the error specifically to the confirmPassword field
            result.rejectValue("confirmPassword", "error.user", e.getMessage());
            return "register";
        } catch (UnderAgeUserException e) {
            // This attaches the error specifically to the birthDate field
            result.rejectValue("birthDate", "error.user", e.getMessage());
            return "register";
        }

//        if (e.getMessage().contains("18 years old")) {
//            // This attaches the error specifically to the birthDate field
//            result.rejectValue("birthDate", "error.user", e.getMessage());
//        } else if (e.getMessage().contains("Passwords do not match!")) {
//            // This attaches the error specifically to the confirmPassword field
//            result.rejectValue("confirmPassword", "error.user", e.getMessage());
//        } else {
//            //If the email exists, we send the error message back to the form
//            model.addAttribute("registrationError", e.getMessage()); //use model because we need to search inside the data first
//        }
    }

    @GetMapping("/login")
    public String Login() {
        return "/login";
    }

    @GetMapping("/welcome")
    public String welcomePage(Model model, Authentication authentication) {
        // Authentication object holds the info of the person who just logged in
        if (authentication != null && authentication.isAuthenticated()) {

            String email = authentication.getName(); //get the email of the user who logged
            User user = userService.findUserByEmail(email); //find the full user object in the DB

            if (user != null) {
                model.addAttribute("username", user.getName());
            }
        }
        return "welcome";
    }

}
