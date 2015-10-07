package com.netflow.stocks.stubs;

import yahoofinance.Stock;
import yahoofinance.quotes.stock.StockQuote;

import java.math.BigDecimal;

public class YahooStockStubs {

    private YahooStockStubs() {
    }

    public static Stock stubStock(String symbol){
        Stock stock = new Stock(symbol);
        stock.setName("Mocked name Inc.");
        stock.setQuote(stubStockQuote(symbol, new BigDecimal("13.98")));
        return stock;
    }

    public static Stock stubUnknownStock(String symbol){
        Stock stock = new Stock(symbol);
        stock.setName("N/A");
        stock.setQuote(stubStockQuote(symbol, BigDecimal.ZERO));
        return stock;
    }

    private static StockQuote stubStockQuote(String symbol, BigDecimal price){
        StockQuote stockQuote = new StockQuote(symbol);
        stockQuote.setPrice(price);
        return stockQuote;
    }

}
