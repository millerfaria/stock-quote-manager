package com.stock.stockquotemanager.controller;

import com.stock.stockquotemanager.model.Quote;
import com.stock.stockquotemanager.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping({"/quotes"})
public class QuoteController {

    @Autowired
    private QuoteService service;

    @GetMapping
    public List<Quote> findAllQuotes() {
        return service.findAllQuotes();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findQuoteById(@PathVariable String id) {
        Optional<Quote> stock = service.findQuoteById(id);

        if (!stock.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(stock.get());
    }

    @PostMapping
    public ResponseEntity<?> createQuote(@RequestBody Quote quote) {
        Quote response = service.createOrUpdateQuote(quote);
        if(response == null){
            return new ResponseEntity<String>("Id not registered on Stock Manager", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(response);
    }
}
