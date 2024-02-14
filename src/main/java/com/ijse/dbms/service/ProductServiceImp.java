package com.ijse.dbms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.dbms.dto.ProductDto;
import com.ijse.dbms.entity.Category;
import com.ijse.dbms.entity.Product;
import com.ijse.dbms.entity.Stock;
import com.ijse.dbms.repository.CategoryRepository;
import com.ijse.dbms.repository.ProductRepository;
import com.ijse.dbms.repository.StockRepository;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

     @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        

        Category category =categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        Stock stock =stockRepository.findById(productDto.getStockId()).orElse(null);
       

        if(category !=null || stock !=null){
           Product product =new Product();
           product.setName(productDto.getName());
           product.setPrice(productDto.getPrice());
           product.setCategory(category);
           product.setStock(stock);
            return productRepository.save(product);
        } else {
             return null;
        }
        
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product updateProduct(Long id, ProductDto productDto) {
        Product existingProduct =productRepository.findById(id).orElse(null);
        if(existingProduct !=null){
           
            Category category =categoryRepository.findById(productDto.getCategoryId()).orElse(null);
            
                if(category !=null) {
                    existingProduct.setName(productDto.getName());
                    existingProduct.setPrice(productDto.getPrice());
                    existingProduct.setCategory(category);
                    return productRepository.save(existingProduct);
                }  else {
                     return null;
                }


        }
        return null;
    }

     @Override
     public List<Product> getProductsByCategory(Long id) {
           Category existingCategory =categoryRepository.findById(id).orElse(null);
           if(existingCategory !=null) {
               return productRepository.findProductByCategory(existingCategory);
           }  else {
               return null;
           }
     }

    @Override
    public boolean deleteProductById(Long id) {
       if(productRepository.existsById(id)) {
           productRepository.deleteById(id);
           return true;
       } else {
          return false;
       }
    }
    
}
