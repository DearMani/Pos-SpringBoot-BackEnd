package com.ijse.dbms.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ijse.dbms.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o join o.user u where u.userName = :userName ")
    List<Order> findOrdersByUserUserName(@Param("userName")  String userName);

    @Query(value ="select * from orders o where o.order_time between :startDate and  :endDate", nativeQuery = true)
    List<Order> findByDateTimeBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    default List<Order> findByToday(LocalDateTime startDate, LocalDateTime endDate) {
        return findByDateTimeBetween(startDate, endDate);
    }

    default List<Order> findByThisWeek(LocalDateTime startDate, LocalDateTime endDate) {
        return findByDateTimeBetween(startDate, endDate);
    }

    default List<Order> findByThisMonth(LocalDateTime startDate, LocalDateTime endDate) {
        return findByDateTimeBetween(startDate, endDate);
    }

    
}