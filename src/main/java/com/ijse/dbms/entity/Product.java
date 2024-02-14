package com.ijse.dbms.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true ,nullable = false)
    private String name;

    private Double price;
    
    @ManyToOne
    @JoinColumn(name ="category_id")
    private Category category;

   
   @OneToOne( fetch = FetchType.EAGER,targetEntity =Stock.class, cascade =CascadeType.ALL)
    @JoinColumn(name ="stockId")
    private Stock stock;

     @JsonIgnore
     @OneToMany(fetch = FetchType.EAGER, mappedBy ="product" ,cascade =CascadeType.ALL)
     private List<OrderDetail> orderDetails =new ArrayList<>();
    
}