package com.bootcamp.stockportfolio.service;

import com.bootcamp.stockportfolio.entity.Stock;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface StockService {
    void uploadStockCSV(MultipartFile file);
    Optional<Stock> getStockDetails(int stockId);
}
