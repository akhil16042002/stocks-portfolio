package com.bootcamp.stockportfolio.repository;

import com.bootcamp.stockportfolio.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TradeRepository extends JpaRepository<Trade, UUID> {
    List<Trade> findByUserIdAndStockId(UUID userId, int stockId);
}
