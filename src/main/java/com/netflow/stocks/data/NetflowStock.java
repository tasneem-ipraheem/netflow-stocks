package com.netflow.stocks.data;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class NetflowStock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String symbol;
    private String name;
    private BigDecimal price;
    private LocalDateTime updated;

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

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("symbol", symbol)
                .add("price", price)
                .add("name", name)
                .toString();

    }
}


