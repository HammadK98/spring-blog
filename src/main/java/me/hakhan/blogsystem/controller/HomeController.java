package me.hakhan.blogsystem.controller;

import me.hakhan.blogsystem.entity.User;
import me.hakhan.blogsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showHome() {
        return "redirect:/posts";
    }

    @GetMapping("/users")
    public String getUser(@RequestParam(name = "userId", required = false) String theUsername, Model  theModel) {
        if (theUsername != null) {
            User theUser = userService.findByUsername(theUsername);
            theModel.addAttribute("user", theUser);
            return "user-info";
        }

        List<User> theUsers = userService.findAllByUsername();
        theModel.addAttribute("theUsers", theUsers);
        return "users-info";

    }
}
