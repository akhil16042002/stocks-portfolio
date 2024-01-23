package com.bootcamp.stockportfolio.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Holding {
    private final int Id;
    private final String name;
    private final int quantity;
    private final double buyPrice;
    private final double currentPrice;
    private final double currentHolding;
    private final double gainOrLoss;
}
