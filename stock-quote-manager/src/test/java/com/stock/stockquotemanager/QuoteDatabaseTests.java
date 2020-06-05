package com.stock.stockquotemanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.stock.stockquotemanager.interfaces.QuoteRepository;
import com.stock.stockquotemanager.model.Quote;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class QuoteDatabaseTests {

    @Autowired
    private QuoteRepository repository;

    @Test
    public void testInsertQuote() {
        Quote quote = new Quote();
        quote.setId("petr4");
        Map<Date, Float> quotations = new HashMap<>();
        Date date = null;
        try {
            date =  new SimpleDateFormat("yyyy-MM-dd").parse("2020-06-05");
        } catch (ParseException e) {
            date = new GregorianCalendar(2020, Calendar.JUNE, 5).getTime();
        }

        quotations.put(date, 10.f);
        quote.setQuotes(quotations);
        repository.save(quote);

        Optional<Quote> stocks = repository.findById("petr4");
        assertNotNull(stocks.get());
    }

}
