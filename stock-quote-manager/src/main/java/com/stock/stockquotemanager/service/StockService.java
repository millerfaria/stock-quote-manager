package com.stock.stockquotemanager.service;

import com.stock.stockquotemanager.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class StockService {

    private static final String SERVER_URL = "http://stock-manager-container:8080";
    private static final String NOTIFICATION_URL = SERVER_URL + "/notification";
    private static final String GET_STOCKS_URL = SERVER_URL + "/stock";

    @Autowired
    private CacheManager cacheManager;

    public StockService(){
        this.registerURLNotificationInStockManager();
    }

    public void registerURLNotificationInStockManager() {
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("host", "localhost");
        bodyMap.put("port", "8081");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity(bodyMap, headers);

        restTemplate.postForLocation(NOTIFICATION_URL, entity);
    }

    @Cacheable("stocks")
    public List<Stock> getStocksFromStockManager()  {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Stock[]> response = restTemplate.getForEntity(GET_STOCKS_URL, Stock[].class);
        return Arrays.asList(response.getBody());
    }

    public void clearCache(){
        cacheManager.getCacheNames().parallelStream().forEach(name -> cacheManager.getCache(name).clear());
    }

}