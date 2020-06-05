package com.stock.stockquotemanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Quote {
    @Id
    private String id;
    @ElementCollection
    @CollectionTable(name = "quotations", joinColumns = {@JoinColumn(name = "stock_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "date")
    @Column(name = "price")
    private Map<Date, Float> quotes;
}