package com.shopsphere.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @GetMapping("/profile")
    public String getProfiler(Authentication authentication){
        return "Welcome " + authentication.getName() + ". Your JWT authentication is successful. ";
    }
}
