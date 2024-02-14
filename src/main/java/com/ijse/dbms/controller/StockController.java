package com.ijse.dbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.dbms.dto.StockDto;
import com.ijse.dbms.entity.Stock;
import com.ijse.dbms.service.StockService;

@RestController
@CrossOrigin(origins = "*")
public class StockController {
     @Autowired
    private StockService stockService;
    
    @GetMapping("/stocks")
    public List<Stock> getAllCategories() {
        return stockService.getAllStocks();
    }
    
    @PostMapping("/stocks")
    public Stock createStock(@RequestBody Stock stock){
        return stockService.createStock(stock);
    }

    @GetMapping("/stocks/{id}")
    public Stock getStockById(@PathVariable Long id) {
       return stockService.getStockById(id);
    }
    
    @PutMapping("/stocks/{id}")
    public Stock updateStock(@PathVariable Long id , @RequestBody StockDto stockDto) {
        return stockService.updateStock(id, stockDto);
    }

    @DeleteMapping("/stocks/{id}")
    public ResponseEntity<?> deleteStockById(@PathVariable Long id) {
          try {
            //status(204)
            return ResponseEntity.ok().body(stockService.deleteStockById(id));
        } catch (Exception e) {
            //status(400)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }

    }
    
}
