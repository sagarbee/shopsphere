package com.shopsphere.security;

import com.shopsphere.entity.ShopUser;
import com.shopsphere.repository.ShopUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShopSphereUserDetailsService implements UserDetailsService {

    private final ShopUserRepository shopUserRepository;

    public ShopSphereUserDetailsService(ShopUserRepository shopUserRepository){
        this.shopUserRepository= shopUserRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ShopUser user = shopUserRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList();

        return new ShopSphereUserDetails(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                authorities,
                user.isEnabled()
        );
    }


/*    @Override
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
 */


}
