package com.ijse.dbms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ijse.dbms.entity.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
    @Query( "select od.qty, p.name " +
            "from OrderDetail od "+
            "join od.product p "+
            "where od.order.id = :id")
    List<Object[]> findOrderDetailByOrderId(@Param("id") Long id);

     @Query( "select od from OrderDetail od where od.order.id = :id")
     List<OrderDetail> findOrderDetailsByOrderId(@Param("id") Long id);
}