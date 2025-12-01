package io.bachelors.nwh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.bachelors.nwh.entity.User;
import io.bachelors.nwh.repository.UserRepo;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/info")
    public User getUserDetails() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepo.findByEmail(email).get();
    }

}
