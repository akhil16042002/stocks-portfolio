package com.bootcamp.stockportfolio.dto;

import com.bootcamp.stockportfolio.entity.TradeType;

import java.util.UUID;

public record CreateTradeRequest(UUID userID, int stockId, int quantity, TradeType tradeType) {

}
