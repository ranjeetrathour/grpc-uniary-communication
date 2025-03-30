package com.example;

import com.example.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, String> {
    Stock findByStockName(String name);
}
