package com.bootcamp.stockportfolio.service;

import com.bootcamp.stockportfolio.dto.CreateTradeRequest;
import com.bootcamp.stockportfolio.dto.Response;
import com.bootcamp.stockportfolio.entity.Trade;
import org.springframework.http.ResponseEntity;

public interface TradeService {
    ResponseEntity<Response<Trade>> createTrade(CreateTradeRequest createTradeRequest);
}
