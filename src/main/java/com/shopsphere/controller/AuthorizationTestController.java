package com.shopsphere.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthorizationTestController {

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "Welcome Admin";
    }

    @GetMapping("/customer/dashboard")
    public String customerDashboard() {
        return "Welcome Customer";
    }

}
