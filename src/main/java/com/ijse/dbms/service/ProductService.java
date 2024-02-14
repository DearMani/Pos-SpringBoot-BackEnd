package com.ijse.dbms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.dbms.dto.ProductDto;
import com.ijse.dbms.entity.Product;

@Service
public interface ProductService {
    List<Product> getAllProducts();
    Product createProduct(ProductDto productDto);
    Product getProductById(Long id);
    Product updateProduct(Long id, ProductDto productDto);
    List<Product> getProductsByCategory(Long id);
    boolean deleteProductById(Long id);

    
}
