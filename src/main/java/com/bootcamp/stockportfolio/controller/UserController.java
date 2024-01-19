package com.bootcamp.stockportfolio.controller;

import com.bootcamp.stockportfolio.dto.Response;
import com.bootcamp.stockportfolio.entity.User;
import com.bootcamp.stockportfolio.service.UserService;
import com.bootcamp.stockportfolio.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/createUser")
    public ResponseEntity<Response<User>> onCreateUser(@RequestBody User user) {
        User getUser = userService.addUser(user);
        return Response.success(HttpStatus.OK, getUser);
    }
}
