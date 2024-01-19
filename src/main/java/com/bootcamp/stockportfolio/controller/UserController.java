package com.bootcamp.stockportfolio.controller;

import com.bootcamp.stockportfolio.entity.User;
import com.bootcamp.stockportfolio.service.UserService;
import com.bootcamp.stockportfolio.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.UUID;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/create-user")
    public ResponseEntity<String> onCreateUser(@RequestBody User user) {
        UUID id = userService.addUser(user);
        return ResponseEntity.ok("Successfully created User with User ID: "+ id);
    }
}
