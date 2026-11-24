package com.shopsphere.service;

import com.shopsphere.dto.ProductRequest;
import com.shopsphere.dto.ProductResponse;
import com.shopsphere.entity.Product;
import com.shopsphere.repository.ProductRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(ProductRequest productRequest){
        Product product = new Product(
                productRequest.name(),
                productRequest.description(),
                productRequest.price(),
                productRequest.stockQuantity()
        );

        Product saved = productRepository.save(product);
        return toResponse(saved);
    }

    public List<ProductResponse> getAllProducts(){
        return productRepository.findByActiveTrue().stream().map(this::toResponse).toList();
    }

    public ProductResponse getProductById(Long id){
        Product product = productRepository.findById(id).filter(Product::isActive).orElseThrow(()->new RuntimeException("Product not found "+id));
        return toResponse(product);
    }

    public ProductResponse updateProduct(Long id, ProductRequest productRequest){
        Product product = productRepository.findById(id).filter(Product::isActive).orElseThrow(()->new RuntimeException("Product not found: "+id));
        product.updateDetails(
                productRequest.name(),
                productRequest.description(),
                productRequest.price(),
                productRequest.stockQuantity()
        );
        return toResponse(product);
    }

    public void deactivateProduct(Long id){
        Product product = productRepository.findById(id).filter(Product::isActive).orElseThrow(()-> new RuntimeException("Product not found: "+id));
        product.deactivate();
    }

    private ProductResponse toResponse(Product product){
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.isActive(),
                product.getCreatedAt()
        );
    }
}
