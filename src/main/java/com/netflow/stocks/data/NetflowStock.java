package com.netflow.stocks.data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class NetflowStock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String symbol;
    private String name;
    private BigDecimal price;
    private Date updated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}


