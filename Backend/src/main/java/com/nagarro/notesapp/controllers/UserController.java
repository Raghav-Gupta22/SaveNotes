package com.nagarro.notesapp.controllers;

import com.nagarro.notesapp.impl.UserServiceImpl;
import com.nagarro.notesapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class UserController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceImpl userService;

    @CrossOrigin("*")
    @PostMapping("/user/register")
    public User register(@RequestBody User user) throws Exception {
        try {
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));
            user.setName(user.getName());
            return this.userService.createUser(user);

        } catch (Exception e) {
            throw new Exception("User with email " + user.getEmail() + " already exists!!");
        }
    }

    @GetMapping("/user/users")
    public List<User> showUser() {
        return this.userService.findAll();
    }
}
