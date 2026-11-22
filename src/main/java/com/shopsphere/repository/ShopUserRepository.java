package com.shopsphere.repository;

import com.shopsphere.entity.ShopUser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopUserRepository extends JpaRepository<ShopUser,Long> {

    @EntityGraph(attributePaths = "roles")
    Optional<ShopUser> findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);

}
