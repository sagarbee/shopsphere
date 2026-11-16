package com.shopsphere.com.shopsphere.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ShopSphereController {

    @GetMapping
    public String getProduct(){
        return "Product list";
    }

    @GetMapping("/me")
    public String getUser(Authentication authentication){
        System.out.println("PRINCIPAL ::"+ authentication.getPrincipal());
        System.out.println("AUTHORITIES ::"+ authentication.getAuthorities());
        System.out.println("CREDENTIALS ::"+ authentication.getCredentials());
        System.out.println("DETAILS ::"+ authentication.getDetails());

        return authentication.getName();
    }



}
