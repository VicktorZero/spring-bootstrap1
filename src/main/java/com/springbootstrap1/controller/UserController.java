package com.springbootstrap1.controller;

import com.springbootstrap1.model.User;
import com.springbootstrap1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String getUserPage(@AuthenticationPrincipal UserDetails userDetails, Model model){
        String email = userDetails.getUsername();
       User user = userService.getUserByUserEmail(email);
        model.addAttribute("user", user);
        return "user";
    }

}
