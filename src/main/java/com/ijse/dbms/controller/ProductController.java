package com.ijse.dbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.dbms.dto.ProductDto;
import com.ijse.dbms.entity.Product;
import com.ijse.dbms.service.ProductService;

@RestController
@CrossOrigin(origins ="*" )//allowing cross origin to all
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        // return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts();
           try {
            //status(201)
            return ResponseEntity.status(200).body(productService.getAllProducts());
        } catch (Exception e) {
            //status(400)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        try {
            //status(201)
            return ResponseEntity.status(201).body(productService.createProduct(productDto));
        } catch (Exception e) {
            //status(400)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Product product =productService.getProductById(id);
        if(product !=null) {
            // return ResponseEntity.ok().body(product);
            //return 200 with body
            //status(200)
             return ResponseEntity.status(HttpStatus.OK).body(product);
           
        } else {
            //return 404
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to create the product");
        }
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }

     @GetMapping("/categories/{id}/products")
     public List<Product> findProductsByCategory(@PathVariable Long id) {
         return productService.getProductsByCategory(id);
     }

     @DeleteMapping("/products/{id}")
     public ResponseEntity<?> deleteProductById(@PathVariable Long id) {
          try {
              return ResponseEntity.ok().body(productService.deleteProductById(id));
          } catch (Exception e) {
               return ResponseEntity.badRequest().body(false);
          }
     }
    
}