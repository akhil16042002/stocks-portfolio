package com.bootcamp.stockportfolio.service;

import com.bootcamp.stockportfolio.dto.Response;
import com.bootcamp.stockportfolio.entity.*;
import com.bootcamp.stockportfolio.repository.StockRepository;
import com.bootcamp.stockportfolio.repository.TradeRepository;
import com.bootcamp.stockportfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class PortfolioServiceImpl implements PortfolioService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    StockRepository stockRepository;

    @Override
    public ResponseEntity<Response<Portfolio>> getPortfolio(UUID userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) {
            return Response.failed(HttpStatus.BAD_REQUEST, "User not found");
        }
        List<Trade> listOfTrades = tradeRepository.findByUserId(userId);
        Map<Integer, List<Trade>> stockIdToTrade = new HashMap<>();
        for(Trade trade: listOfTrades) {
            int stockId = trade.getStockId();
            if (stockIdToTrade.containsKey(stockId)) {
                stockIdToTrade.get(stockId).add(trade);
            }
            else {
                List<Trade> tradesForStockId = new ArrayList<>();
                tradesForStockId.add(trade);
                stockIdToTrade.put(stockId, tradesForStockId);
            }
        }
        double totalPortfolioHoldings = 0;
        double totalBuyPrice = 0;
        double totalGainOrLoss = 0;
        double totalGainOrLossPercentage = 0;
        List<Holding> listOfHoldings = new ArrayList<>();
        for (Map.Entry<Integer, List<Trade>> entry : stockIdToTrade.entrySet()) {
            int stockId = entry.getKey();
            Optional<Stock> optionalStock = stockRepository.findById(stockId);
            if(optionalStock.isEmpty()) {
                return Response.failed(HttpStatus.BAD_REQUEST, "Stock not found");
            }
            Stock stock = optionalStock.get();
            String stockName = stock.getName();
            double totalAverageBuyPrice = 0;
            int totalBuyQuantity = 0;
            int totalQuantity = 0;
            int buyQuantity = 0;
            int sellQuantity = 0;
            double sumOfBuyPriceTimesQuantity = 0;
            double currentPrice = stock.getLastTradedPrice();
            List<Trade> tradesForStockId = entry.getValue();
            for (Trade trade : tradesForStockId) {
                if(trade.getTradeType() == TradeType.BUY) {
                    double buyPrice = trade.getPrice();
                    buyQuantity = trade.getQuantity();
                    sumOfBuyPriceTimesQuantity += buyPrice * buyQuantity;
                    totalBuyQuantity += buyQuantity;
                    totalQuantity += buyQuantity;
                }
                else {
                    totalQuantity -= trade.getQuantity();;
                }
            }
            totalAverageBuyPrice = sumOfBuyPriceTimesQuantity / totalBuyQuantity;
            double currentHolding = totalAverageBuyPrice * totalQuantity;
            double gainOrLoss = currentPrice * totalQuantity - currentHolding;
            totalPortfolioHoldings += currentPrice * totalQuantity;
            totalBuyPrice += sumOfBuyPriceTimesQuantity;
            totalGainOrLoss += gainOrLoss;
            Holding holding = new Holding(stockId, stockName, totalQuantity, totalAverageBuyPrice, currentPrice, currentHolding, gainOrLoss);
            listOfHoldings.add(holding);
        }
        totalGainOrLossPercentage = totalGainOrLoss / totalBuyPrice * 100;
        Portfolio portfolio = new Portfolio(listOfHoldings, totalPortfolioHoldings, totalBuyPrice, totalGainOrLoss, totalGainOrLossPercentage);
        return Response.success(HttpStatus.OK, portfolio);
    }
}
