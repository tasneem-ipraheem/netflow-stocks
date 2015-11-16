package com.netflow.stocks.api;

import io.swagger.annotations.ApiModel;

import javax.xml.bind.annotation.XmlRootElement;

@ApiModel(value="LookupResultDto")
@XmlRootElement(name = "lookupResult")
public class LookupResultDto {

    private String symbol;
    private String name;
    private String exchange;

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

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
}
