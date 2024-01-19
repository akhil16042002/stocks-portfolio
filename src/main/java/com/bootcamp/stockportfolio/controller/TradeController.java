package com.bootcamp.stockportfolio.controller;

import com.bootcamp.stockportfolio.dto.CreateTradeRequest;
import com.bootcamp.stockportfolio.dto.Response;
import com.bootcamp.stockportfolio.entity.Trade;
import com.bootcamp.stockportfolio.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TradeController {

    @Autowired
    TradeService tradeService;

    @PostMapping(value = "/createTrade")
    public ResponseEntity<Response<Trade>> onCreateTrade(@RequestBody CreateTradeRequest createTradeRequest) {
        return tradeService.createTrade(createTradeRequest);
    }
}
