package com.bootcamp.stockportfolio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    private final int id;
    private final String name;
    @Column(name = "`group`")
    private final String group;
    private final String type;
    private final double openingPrice;
    private final double highestPrice;
    private final double lowestPrice;
    private final double closingPrice;
    private final double lastTradedPrice;
    private final double previousClosingPrice;
    private int numberOfTrades;
    private int numberOfShares;
    private final double netTurnover;
    private final String tradingAndClearingIndicator;

    public Stock(int id, String name, String group, String type, double openingPrice, double highestPrice, double lowestPrice, double closingPrice, double lastTradedPrice, double previousClosingPrice, int numberOfTrades, int numberOfShares, double netTurnover, String tradingAndClearingIndicator) {

        this.id = id;
        this.name = name;
        this.group = group;
        this.type = type;
        this.openingPrice = openingPrice;
        this.highestPrice = highestPrice;
        this.lowestPrice = lowestPrice;
        this.closingPrice = closingPrice;
        this.lastTradedPrice = lastTradedPrice;
        this.previousClosingPrice = previousClosingPrice;
        this.numberOfTrades = numberOfTrades;
        this.numberOfShares = numberOfShares;
        this.netTurnover = netTurnover;
        this.tradingAndClearingIndicator = tradingAndClearingIndicator;
    }

    public void updateStockQuantity(Trade trade) {
        if (trade.getTradeType() == TradeType.BUY) {
            numberOfShares -= trade.getQuantity();
        } else {
            numberOfShares += trade.getQuantity();

            numberOfTrades += 1;
        }
    }
}
