package com.shopsphere.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopSphereUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final String encodedPassword;

    public ShopSphereUserDetailsService(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.encodedPassword = passwordEncoder.encode("Sagar@1234");
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if("sagar@mail.com".equalsIgnoreCase(email)){
            return new ShopSphereUserDetails(1L,"sagar@mail.com",encodedPassword,
                    List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER"),new SimpleGrantedAuthority("PRODUCT_READ")),true);
        }
        if("admin@mail.com".equalsIgnoreCase(email)){
            return new ShopSphereUserDetails(2L,"admin@mail.com",encodedPassword,
                    List.of(new SimpleGrantedAuthority("ROLE_ADMIN")),true);
        }
        throw new UsernameNotFoundException("User Not found: "+ email);
    }
}
