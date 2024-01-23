package com.bootcamp.stockportfolio.controller;

import com.bootcamp.stockportfolio.dto.Response;
import com.bootcamp.stockportfolio.entity.Portfolio;
import com.bootcamp.stockportfolio.entity.Trade;
import com.bootcamp.stockportfolio.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

    @GetMapping(value = "/portfolio/{userId}")
    public ResponseEntity<Response<Portfolio>> onPortfolioDetail(@PathVariable UUID userId) {
        return portfolioService.getPortfolio(userId);
    }
}
