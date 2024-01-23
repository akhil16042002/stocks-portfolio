package com.bootcamp.stockportfolio.service;

import com.bootcamp.stockportfolio.dto.Response;
import com.bootcamp.stockportfolio.entity.Portfolio;
import com.bootcamp.stockportfolio.entity.Trade;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface PortfolioService {
    ResponseEntity<Response<Portfolio>> getPortfolio(UUID userId);
}
