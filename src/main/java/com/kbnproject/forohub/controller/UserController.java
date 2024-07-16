package com.kbnproject.forohub.controller;

import com.kbnproject.forohub.dto.RequestUser;
import com.kbnproject.forohub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody RequestUser requestUser){
        return userService.login(requestUser.getEmail(), requestUser.getPassword());
    }

    @PostMapping("/signup")
    public String register(@RequestBody RequestUser requestUser){
        try {
            return userService.register(requestUser.getEmail(), requestUser.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
