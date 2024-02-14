package com.ijse.dbms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Stock {
    
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer Id;

    @Column(unique = true)
    private String stockId;

    private Integer qty;

    private String location;
    
    @JsonIgnore
    @OneToOne(mappedBy ="stock", targetEntity =Product.class)
    private Product product;
    
}
