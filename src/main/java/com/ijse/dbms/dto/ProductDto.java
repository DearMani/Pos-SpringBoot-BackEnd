package com.ijse.dbms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    
    private String name;
    private Double price;
    private Long categoryId;
    private Long stockId;
}
