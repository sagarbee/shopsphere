package com.shopsphere.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class ShopSphereUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final String encodedPassword;

    public ShopSphereUserDetailsService(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.encodedPassword = passwordEncoder.encode("Sagar@1234");
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if(!"sagar@mail.com".equalsIgnoreCase(email)){
            throw new UsernameNotFoundException("User not found: "+ email);
        }
        return new ShopSphereUserDetails(1L,"sagar@mail.com",encodedPassword, List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER")),true);
    }
}
