package com.stock.stockquotemanager.service;

import com.stock.stockquotemanager.interfaces.QuoteRepository;
import com.stock.stockquotemanager.model.Quote;
import com.stock.stockquotemanager.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuoteService {

    @Autowired
    private QuoteRepository repository;

    @Autowired
    private StockService stockManagerService;

    public List<Quote> findAllQuotes() {

        return repository.findAll();
    }

    public Optional<Quote> findQuoteById(String id) {
        return repository.findById(id);
    }

    public Quote createOrUpdateQuote(Quote quote) {

        List<Stock> stocks = stockManagerService.getStocksFromStockManager();
        Stock stock = stocks.stream()
                .filter(stockItem -> quote.getId().equals(stockItem.getId())).findFirst().orElse(null);
        if(stock == null) {
            return null;
        }
        return repository.save(quote);
    }

}
