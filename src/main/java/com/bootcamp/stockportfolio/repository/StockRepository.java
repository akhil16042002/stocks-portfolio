package com.bootcamp.stockportfolio.repository;

import com.bootcamp.stockportfolio.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
}
