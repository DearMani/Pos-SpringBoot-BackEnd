package com.ijse.dbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.dbms.service.OrderDetailService;

@RestController
@CrossOrigin(origins = "*")
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;

       @GetMapping("/orders/{id}/orderDetails")
       public ResponseEntity<?> getOrderDetailsByOrderId(@PathVariable Long id){
          List<Object[]> list =(orderDetailService.findOrderDetailsByOrderId(id));

          for(Object[] array : list) {
              System.out.println(array[0]+"      "+array[1]);
          }

         return ResponseEntity.status(200).body(list);
    }
    
}
