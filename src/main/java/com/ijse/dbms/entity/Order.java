package com.ijse.dbms.entity;

import java.time.LocalDateTime;
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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.JoinColumn;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderTime;

    @Column(nullable = false)
    private Double total;

    private Double discount;

    @Column(nullable = false)
    private Double tax;

     @ManyToOne(targetEntity =User.class)
    @JoinColumn(name="userId")
    private User user;
     
     @JsonIgnore
     @OneToMany(  fetch = FetchType.EAGER,mappedBy ="order" , cascade = CascadeType.ALL  )
     private List<OrderDetail> orderDetails =new ArrayList<>();


    // @ManyToMany
    // @JoinTable(
    //        name = "order_product",
    //        joinColumns = @JoinColumn(name = "order_id"),
    //        inverseJoinColumns = {
    //            @JoinColumn(name = "product_id"),
    //            @JoinColumn(name = "qty" , columnDefinition = "integer")
    //        }
    // )
    // private List<Product> products = new ArrayList<>();

    
}
