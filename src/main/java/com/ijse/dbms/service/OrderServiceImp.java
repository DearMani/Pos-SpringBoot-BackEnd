package com.ijse.dbms.service;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ijse.dbms.dto.OrderDetailDto;
import com.ijse.dbms.dto.OrderDto;
import com.ijse.dbms.entity.Order;
import com.ijse.dbms.entity.OrderDetail;
import com.ijse.dbms.entity.Product;
import com.ijse.dbms.entity.Stock;
import com.ijse.dbms.entity.User;
import com.ijse.dbms.repository.OrderDetailRepository;
import com.ijse.dbms.repository.OrderRepository;
import com.ijse.dbms.repository.ProductRepository;
import com.ijse.dbms.repository.StockRepository;
import com.ijse.dbms.repository.UserRepository;

@Service
public class OrderServiceImp implements OrderService{
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    StockRepository stockRepository;

    @Autowired
    UserRepository userRepository;
     @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public List<Order> getAllOrders() {
         return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Boolean placeOrder(OrderDto orderDto) {

     User existingUser =userRepository.findById(orderDto.getCustomerId()).orElse(null);
     if(existingUser !=null) {
          Order newOrder =new Order();
          newOrder.setUser(existingUser);
          newOrder.setOrderTime(LocalDateTime.now());
          newOrder.setDiscount(orderDto.getDiscount());
          newOrder.setTax(orderDto.getTax());
          newOrder.setTotal(orderDto.getTotal());
          Order savOrder =orderRepository.save(newOrder);
            System.out.println("***************************************************************************   111111111111111111");
          if( savOrder !=null) {
                   
                for(OrderDetailDto dto : orderDto.getOrderDetails()) {
                     System.out.println("***************************************************************************  222222222222222222");
                     System.out.println(dto.getProductid() +"   "+ dto.getQty());
                    Product product =productRepository.findById( dto.getProductid()).orElse(null);
                    if(product !=null && product.getStock().getQty() >=Integer.parseInt(dto.getQty())) {
                        OrderDetail orderDetail =new OrderDetail();
                        orderDetail.setOrder(savOrder);
                        orderDetail.setProduct(product);
                        orderDetail.setQty(Integer.parseInt(dto.getQty()));
                      
                      OrderDetail saveOrderDetail =orderDetailRepository.save(orderDetail);

                         if( saveOrderDetail !=null) {
                            Stock stock =product.getStock();
                            stock.setQty(stock.getQty() -Integer.parseInt(dto.getQty()));
                            
                            Stock saveStock =stockRepository.save(stock);
                            
                            if(saveStock !=null) {
                                 return true;
                            }
                            
                            
                            
                            else {
                                return false;
                            }


                         }  else {
                            return false;
                         }             

                } else {
    
                    return false;
                }
          }
         

     } else {
        System.out.println(" save order fail");
        return false;
     }
     
   
  }  
    System.out.println(" user doesnot exists ");
  return false;

    }

    @Override
    public List<Order> getOrdersByUserName(String userName) {
      
        return orderRepository.findOrdersByUserUserName(userName);


    }

    @Override
    public List<Order> getOrdersByTimeInterval(String timeInterval) {
         LocalDateTime start;
         LocalDateTime end;
         
         switch(timeInterval){
            case "today":
             start =LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
             end =LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(0);
             return orderRepository.findByToday(start, end);
             
             case "week":
              start =LocalDateTime.now().with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY)).withHour(0).withMinute(0).withSecond(0).withNano(0);
              end =LocalDateTime.now().with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY)).withHour(23).withMinute(59).withSecond(59).withNano(0);
              return orderRepository.findByThisWeek(start, end);

             case "month":
              start =LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0).withNano(0);
              end =LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59).withNano(0);
              return orderRepository.findByThisMonth(start, end);

             case "all":
               return orderRepository.findAll();
               default:
                  return null;
              //  throw new IllegalArgumentException("Invalid time interval: "+timeInterval);

         }


    }

    @Override
    public boolean deleteOrderById(Long id) {
         if(orderRepository.existsById(id)) {
               orderRepository.deleteById(id);
               return true;
         }   else {
              return false;
         }
    }

}
