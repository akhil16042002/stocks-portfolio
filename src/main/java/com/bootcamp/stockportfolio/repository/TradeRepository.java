package com.bootcamp.stockportfolio.repository;

import com.bootcamp.stockportfolio.dto.Response;
import com.bootcamp.stockportfolio.entity.Portfolio;
import com.bootcamp.stockportfolio.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TradeRepository extends JpaRepository<Trade, UUID> {
    List<Trade> findByUserIdAndStockId(UUID userId, int stockId);
    List<Trade> findByUserId(UUID userId);
}
