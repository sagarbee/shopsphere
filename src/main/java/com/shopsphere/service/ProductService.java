package com.shopsphere.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @PreAuthorize("hasRole('ADMIN')")
    public String createProduct(){
        return "Product has been created";
    }

    @PreAuthorize("hasAuthority('PRODUCT_READ')")
    public String viewProducts(){
        return "Product list...";
    }

}
