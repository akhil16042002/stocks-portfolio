package com.bootcamp.stockportfolio.entity;

import com.bootcamp.stockportfolio.dto.CreateTradeRequest;
import com.bootcamp.stockportfolio.repository.StockRepository;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

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
    private final BigDecimal price;

    public Trade(CreateTradeRequest createTradeRequest, Stock stock) {
        userId = createTradeRequest.userID();
        stockId = createTradeRequest.stockId();
        quantity = createTradeRequest.quantity();
        tradeType = createTradeRequest.tradeType();
        price = BigDecimal.valueOf(stock.getLastTradedPrice());
    }
}
