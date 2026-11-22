package com.shopsphere.dto;

public record RegisterResponse(
        Long userId,
        String email,
        String message

) {

}
