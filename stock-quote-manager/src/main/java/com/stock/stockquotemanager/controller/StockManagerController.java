package com.stock.stockquotemanager.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/stockcache"})
public class StockManagerController {

    @DeleteMapping
    public void delete() {
        System.out.println("Deletando...");
    }
}