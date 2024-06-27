package com.bootcamp.stockportfolio.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

public record Portfolio(List<Holding> holding, double totalPortfolioHolding, double totalBuyPrice, double totalProfitOrLoss, double getTotalProfitOrLossPercentage) {
}
