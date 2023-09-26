package me.hakhan.blogsystem.controller;

import me.hakhan.blogsystem.entity.User;
import me.hakhan.blogsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/showFormForLogin")
    public String showFormForLogin() {
        return "login-form";
    }

    @GetMapping("/login")
    public String login() {
        return "redirect:/showFormForLogin";
    }

    @GetMapping("/showRegistrationForm")
    public String showRegistrationForm(Model theModel) {
        theModel.addAttribute("theUser", new User());
        return "register-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(@Valid @ModelAttribute("theUser") User theUser, BindingResult theBindingResult, Model theModel) {

        if (!bCryptPasswordEncoder.matches(theUser.getMatchingPassword(), bCryptPasswordEncoder.encode(theUser.getPassword()))) {
            theModel.addAttribute("registrationError", "Passwords do not match");
            return "register-form";
        }
//        if (theUser.getPassword() != theUser.getMatchingPassword()) {
//            theModel.addAttribute("registrationError", "Passwords do not match");
//            return "register-form";
//        }

        if (theBindingResult.hasErrors()) {
            return "register-form";
        }

        User existingUsername = userService.findByUsername(theUser.getUsername());
        User existingEmail = userService.findByEmail(theUser.getEmail());

        if (existingUsername != null || existingEmail != null) {
            theModel.addAttribute("registrationError", "User with given email/username already exists");
            return "register-form";
        }

        try {
            userService.save(theUser);
        } catch (Exception e) {
            theModel.addAttribute("registrationError", "Something went wrong.");
            return "register-form";
        }

        theModel.addAttribute("registrationSuccess", "New user with username " + theUser.getUsername() +" created successfully. Please login");
        return "login-form";
    }
}
