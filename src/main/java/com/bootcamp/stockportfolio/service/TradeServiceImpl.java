package com.bootcamp.stockportfolio.service;

import com.bootcamp.stockportfolio.dto.CreateTradeRequest;
import com.bootcamp.stockportfolio.dto.Response;
import com.bootcamp.stockportfolio.entity.Stock;
import com.bootcamp.stockportfolio.entity.Trade;
import com.bootcamp.stockportfolio.entity.TradeType;
import com.bootcamp.stockportfolio.entity.User;
import com.bootcamp.stockportfolio.repository.StockRepository;
import com.bootcamp.stockportfolio.repository.TradeRepository;
import com.bootcamp.stockportfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    StockRepository stockRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<Response<Trade>> createTrade(CreateTradeRequest createTradeRequest) {
        Optional<User> optionalUser = userRepository.findById(createTradeRequest.userId());
        if(optionalUser.isEmpty()) {
            return Response.failed(HttpStatus.BAD_REQUEST, "User not found");
        }
        Optional<Stock> optionalStock = stockRepository.findById(createTradeRequest.stockId());
        if(optionalStock.isEmpty()) {
            return Response.failed(HttpStatus.BAD_REQUEST, "Stock not found");
        }
        Stock stock = optionalStock.get();
        Trade trade = new Trade(createTradeRequest, stock);
        if(trade.getTradeType() == TradeType.BUY) {
            if(trade.getQuantity() > stock.getNumberOfShares()) {
                return Response.failed(HttpStatus.BAD_REQUEST, "Insufficient number of shares");
            }
            tradeRepository.save(trade);
            stock.updateStockQuantity(trade);
            stockRepository.save(stock);
            return Response.success(HttpStatus.OK, trade);
        }
        List<Trade> tradeList = tradeRepository.findByUserIdAndStockId(trade.getUserId(), trade.getStockId());
        int totalBuyQuantity = 0, totalSellQuantity = 0;
        for (Trade currentTrade : tradeList) {
            if (TradeType.BUY.equals(currentTrade.getTradeType())) {
                totalBuyQuantity += currentTrade.getQuantity();
            }
            else {
                totalSellQuantity += currentTrade.getQuantity();
            }
        }
        if(trade.getQuantity() > totalBuyQuantity - totalSellQuantity) {
            return Response.failed(HttpStatus.BAD_REQUEST, "Insufficient number of shares to sell");
        }
        tradeRepository.save(trade);
        stock.updateStockQuantity(trade);
        stockRepository.save(stock);
        return Response.success(HttpStatus.OK, trade);
    }
}
