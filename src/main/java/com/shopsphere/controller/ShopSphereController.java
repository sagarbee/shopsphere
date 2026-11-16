package com.shopsphere.controller;

import com.shopsphere.service.CurrentUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ShopSphereController {

    private final CurrentUserService currentUserService;

    public ShopSphereController(CurrentUserService currentUserService){
        this.currentUserService = currentUserService;
    }

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
    @GetMapping("/me/context")
    public String getUserFromSecurityContextHolder(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        return authentication.getName();
    }
    @GetMapping("/me/username")
    public String getCurrentUsername(){
        return currentUserService.getCurrentUsername();
    }

    @GetMapping("/me/auth-details")
    public Map<String, Object> authDetails(Authentication authentication){
        Map<String,Object> result = new HashMap<>();
        result.put("NAME :: ",authentication.getName());
        result.put("AUTHENTICATED :: ",authentication.isAuthenticated());
        result.put("AUTHORITIES :: ",authentication.getAuthorities());
        result.put("PRINCIPAL TYPE :: ",authentication.getPrincipal().getClass().getName());
        return result;
    }

}
