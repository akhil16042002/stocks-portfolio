package com.bootcamp.stockportfolio.service;

import com.bootcamp.stockportfolio.entity.Stock;
import com.bootcamp.stockportfolio.repository.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@SpringBootTest
class StockServiceTest {
    @Autowired
    private StockService stockService;

    @Autowired
    private StockRepository stockRepository;

    @BeforeEach
    void setup() {
        Stock stock = new Stock(500032, "BAJAJHIND", "A", "Q", 28.19, 28.49, 27.9, 28.14, 28.14, 27.84, 5176, 1485819, 41943333, "");
        stockRepository.save(stock);
    }

    @AfterEach
    void tearDown() {
        stockRepository.deleteAll();
    }

    @Test
    void expectStockDetailsWhenValidStockIdIsGiven() {
        int stockId = 500032;
        Optional<Stock> optionalStock = stockService.getStockDetails(stockId);
        Stock stock = optionalStock.get();
        assertEquals(stock.getId(), stockId);
    }

    @Test
    void expectNoStockDetailsWhenValidStockIdIsGiven() {
        int inValidStockId = 50032590;
        Optional<Stock> optionalStock = stockService.getStockDetails(inValidStockId);
        assertTrue(optionalStock.isEmpty());
    }
}