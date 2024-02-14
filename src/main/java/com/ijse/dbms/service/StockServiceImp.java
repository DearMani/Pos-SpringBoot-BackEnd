package com.ijse.dbms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.dbms.dto.StockDto;
import com.ijse.dbms.entity.Stock;
import com.ijse.dbms.repository.StockRepository;

@Service
public class StockServiceImp  implements StockService{
 
    @Autowired
    private StockRepository stockRepository;


    @Override
    public List<Stock> getAllStocks() {
       return stockRepository.findAll();
    }

    @Override
    public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Stock getStockById(Long id) {
        return stockRepository.findById(id).orElse(null);
    }

    @Override
    public Stock updateStock(Long id, StockDto stockDto) {
        Stock existingStock =stockRepository.findById(id).orElse(null);
        if(existingStock !=null) {
              existingStock.setStockId(stockDto.getStockId());
              existingStock.setQty(stockDto.getQty());
             existingStock.setLocation(stockDto.getLocation());

              return stockRepository.save(existingStock);
        }  else {
             return null;
        }
    }

    @Override
    public boolean deleteStockById(Long id) {
        if(stockRepository.existsById(id)) {
            stockRepository.deleteById(id);
            return true;
        } else {
           return false;
        }
    }

    
}
