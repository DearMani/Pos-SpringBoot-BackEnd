package com.ijse.dbms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.dbms.dto.StockDto;
import com.ijse.dbms.entity.Stock;

@Service
public interface StockService {
      List<Stock> getAllStocks();
    Stock createStock(Stock stock);
    Stock getStockById(Long id);
    Stock updateStock(Long id, StockDto stockDto);
    boolean deleteStockById(Long id);
}
