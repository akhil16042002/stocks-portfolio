package com.bootcamp.stockportfolio.service;

import com.bootcamp.stockportfolio.entity.Stock;
import com.bootcamp.stockportfolio.repository.StockRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    StockRepository stockRepository;

    @Transactional
    @Override
    public void uploadStockCSV(MultipartFile file) {
        try {
            CSVParser parser = CSVParser.parse(file.getInputStream(), StandardCharsets.UTF_8, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            List<CSVRecord> records = parser.getRecords();
            for (var record : records) {
                System.out.println(record);
                Stock stock = new Stock(
                        Integer.parseInt(record.get("SC_CODE")),
                        record.get("SC_NAME"),
                        record.get("SC_GROUP"),
                        record.get("SC_TYPE"),
                        Double.parseDouble(record.get("OPEN")),
                        Double.parseDouble(record.get("HIGH")),
                        Double.parseDouble(record.get("LOW")),
                        Double.parseDouble(record.get("CLOSE")),
                        Double.parseDouble(record.get("LAST")),
                        Double.parseDouble(record.get("PREVCLOSE")),
                        Integer.parseInt(record.get("NO_TRADES")),
                        Integer.parseInt(record.get("NO_OF_SHRS")),
                        Double.parseDouble(record.get("NET_TURNOV")),
                        record.get("TDCLOINDI")
                );
                stockRepository.save(stock);
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
    @Override
    public Optional<Stock> getStockDetails(int stockId) {
        return stockRepository.findById(stockId);
    }
}
