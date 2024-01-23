package com.bootcamp.stockportfolio.controller;

import com.bootcamp.stockportfolio.dto.Response;
import com.bootcamp.stockportfolio.entity.Stock;
import com.bootcamp.stockportfolio.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
public class StockController {
    @Autowired
    StockService stockService;

    @PostMapping(value = "/uploadStockCSV")
    public ResponseEntity<Response<String>> onUploadStockCSV(@RequestBody MultipartFile file) {
        try {
            stockService.uploadStockCSV(file);
            return Response.success(HttpStatus.OK,"Successfully uploaded");
        } catch (RuntimeException e) {
            return Response.failed(HttpStatus.UNPROCESSABLE_ENTITY, "Failed to process CSV file");
        }
    }

    @GetMapping(value = "/stocks/{stockId}")
    public ResponseEntity<Response<Stock>> onStockDetails(@PathVariable int stockId) {
        Optional<Stock> optionalStock = stockService.getStockDetails(stockId);
        return optionalStock.map(stock -> Response.success(HttpStatus.OK, stock))
                .orElseGet(() -> Response.failed(HttpStatus.BAD_REQUEST, "Stock not found"));
    }
}
