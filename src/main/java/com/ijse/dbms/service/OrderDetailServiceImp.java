package com.ijse.dbms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.dbms.dto.OrderDto;
import com.ijse.dbms.entity.Order;
import com.ijse.dbms.entity.OrderDetail;
import com.ijse.dbms.repository.OrderDetailRepository;

@Service
public class OrderDetailServiceImp implements OrderDetailService{
 
   @Autowired
   OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> getAllOrderDetails() {
          return orderDetailRepository.findAll();
    }

    @Override
    public OrderDetail createOrderDetail(Order order,OrderDto orderDto) {
          OrderDetail newOrderDetail =new OrderDetail();
          newOrderDetail.setOrder(order);
          newOrderDetail.setProduct(null);
          return null;
    }

    @Override
    public OrderDetail getOrderDetailById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrderDetailById'");
    }

    @Override
    public OrderDetail updateOrderDetail(Long id, OrderDto orderDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOrderDetail'");
    }

    @Override
    public boolean deleteOrderDetailById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteOrderDetailById'");
    }

    @Override
    public OrderDetail savOrderDetail(OrderDetail orderDetail) {
             return orderDetailRepository.save(orderDetail);
    
}

@Override
public List<Object[]> findOrderDetailsByOrderId(Long id) {
      return orderDetailRepository.findOrderDetailByOrderId(id);
}

}
