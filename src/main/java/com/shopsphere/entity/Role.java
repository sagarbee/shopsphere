package com.shopsphere.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    protected Role(){}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
