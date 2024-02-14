package com.ijse.dbms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.dbms.dto.OrderDto;
import com.ijse.dbms.dto.ProductDto;
import com.ijse.dbms.entity.Order;
import com.ijse.dbms.entity.OrderDetail;
import com.ijse.dbms.entity.Product;

@Service
public interface OrderDetailService {
     List<OrderDetail> getAllOrderDetails();
    OrderDetail createOrderDetail(Order order,OrderDto orderDto);
    OrderDetail getOrderDetailById(Long id);
    OrderDetail updateOrderDetail(Long id, OrderDto orderDto);
    boolean deleteOrderDetailById(Long id);
    OrderDetail savOrderDetail(OrderDetail orderDetail);
    List<Object[]> findOrderDetailsByOrderId(Long id);
}
