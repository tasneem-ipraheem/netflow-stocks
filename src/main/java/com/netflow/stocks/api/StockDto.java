package com.netflow.stocks.api;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "stock")
public class StockDto {

    private String symbol;

    private String name;

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
}
