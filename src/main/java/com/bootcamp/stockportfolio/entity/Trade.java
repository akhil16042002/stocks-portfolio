package com.bootcamp.stockportfolio.entity;

import com.bootcamp.stockportfolio.dto.CreateTradeRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor(force = true)
@Entity
@Table(name = "trades")
public class Trade {
    @Id
    private final UUID id = UUID.randomUUID();
    private final UUID userId;
    private final int stockId;
    private final int quantity;
    @Enumerated(value = EnumType.STRING)
    private final TradeType tradeType;
    @Column(columnDefinition = "NUMERIC(10, 4)")
    private final double price;

    public Trade(CreateTradeRequest createTradeRequest, Stock stock) {
        userId = createTradeRequest.userId();
        stockId = createTradeRequest.stockId();
        quantity = createTradeRequest.quantity();
        tradeType = createTradeRequest.tradeType();
        price = stock.getLastTradedPrice();
    }
}
