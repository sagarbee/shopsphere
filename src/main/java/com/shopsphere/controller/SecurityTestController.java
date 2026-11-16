package com.shopsphere.controller;

import com.shopsphere.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class SecurityTestController {

    private final ProductService productService;

    public SecurityTestController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/create-product")
    public String createProduct() {
        return productService.createProduct();
    }

    @GetMapping("/products")
    public String viewProducts() {
        return productService.viewProducts();
    }

}
