package com.ijse.dbms.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.dbms.dto.OrderDto;
import com.ijse.dbms.entity.Order;
import com.ijse.dbms.entity.OrderDetail;
import com.ijse.dbms.service.OrderService;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllProducts() {   
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders());
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
         return ResponseEntity.status(200).body(orderService.getOrderById(id));
    } 

    @PostMapping("/orders")
    public ResponseEntity<?> placeOrder(@RequestBody OrderDto orderDto) {
          boolean placeOrder = orderService.placeOrder(orderDto);
          return ResponseEntity.status(201).body(placeOrder);
    }

    @GetMapping("/users/{userName}/orders")
    public ResponseEntity<?> getOrdersByUserName(@PathVariable  String userName) {
          return ResponseEntity.status(200).body(orderService.getOrdersByUserName(userName));
    }

    @GetMapping("/orders/{timeInterval}/time-interval")
    public ResponseEntity<?> getOrderByTimeInterval(@PathVariable String timeInterval) {
        List<Order> list =orderService.getOrdersByTimeInterval(timeInterval);
         for(Order o : list){
            System.out.println(o.getId());
         }
           return ResponseEntity.status(200).body(list);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable Long id) {
          return ResponseEntity.status(204).body(orderService.deleteOrderById(id));
    }

    
}
