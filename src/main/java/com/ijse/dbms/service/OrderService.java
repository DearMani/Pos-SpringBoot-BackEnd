package com.ijse.dbms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.dbms.dto.OrderDto;
import com.ijse.dbms.entity.Order;

@Service
public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Boolean placeOrder(OrderDto OrderDto);
    List<Order>getOrdersByUserName(String userName);
    List<Order>getOrdersByTimeInterval(String timeInterval);
    boolean deleteOrderById(Long id);
    

}
