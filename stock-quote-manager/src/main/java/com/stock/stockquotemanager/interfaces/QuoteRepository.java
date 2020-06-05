package com.stock.stockquotemanager.interfaces;

import com.stock.stockquotemanager.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, String> {}
