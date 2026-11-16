package com.shopsphere.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordExperiment {

    public static void main(String [] args){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "MyPassword##";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Encoded Password :: "+ encodedPassword);
        System.out.println(encoder.matches(rawPassword,encodedPassword));
        System.out.println(encoder.matches("wrongPassword",encodedPassword));
    }

}
