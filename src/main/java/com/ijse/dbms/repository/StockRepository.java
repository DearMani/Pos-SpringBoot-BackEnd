package com.ijse.dbms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.dbms.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {
    
}
