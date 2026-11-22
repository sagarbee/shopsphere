package com.shopsphere.service;

import com.shopsphere.dto.RegisterRequest;
import com.shopsphere.dto.RegisterResponse;
import com.shopsphere.entity.Role;
import com.shopsphere.entity.ShopUser;
import com.shopsphere.repository.RoleRepository;
import com.shopsphere.repository.ShopUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RegistrationService {

    private final ShopUserRepository shopUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(ShopUserRepository shopUserRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder){
        this.shopUserRepository = shopUserRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public RegisterResponse register(RegisterRequest request){
        if(shopUserRepository.existsByEmailIgnoreCase(request.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Email is already registered");
        }

        Role customerRole = roleRepository.findByName("ROLE_CUSTOMER")
                .orElseThrow(() -> new IllegalStateException("ROLE_CUSTOMER has not been configured"));

        String hashedPassword = passwordEncoder.encode(request.password());
        ShopUser user = new ShopUser(request.email().trim(),hashedPassword);
        user.addRole(customerRole);
        ShopUser savedUser = shopUserRepository.save(user);
        return new RegisterResponse(savedUser.getId(), savedUser.getEmail(), "Registration successful");
    }
}
