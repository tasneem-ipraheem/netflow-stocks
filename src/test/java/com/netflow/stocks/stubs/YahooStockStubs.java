package com.netflow.stocks.stubs;

import com.netflow.stocks.service.load.yahoo.YahooAsset;

import java.math.BigDecimal;

public class YahooStockStubs {

    private YahooStockStubs() {
    }

    public static YahooAsset stubYahooAsset(String symbol){
        YahooAsset stock = new YahooAsset();
        stock.setName("Mocked name Inc.");
        stock.setSymbol(symbol);
        stock.setLastTradePriceOnly("13.98");
        return stock;
    }

    public static YahooAsset stubUnknownYahooAsset(String symbol){
        YahooAsset stock = new YahooAsset();
        stock.setName("N/A");
        stock.setSymbol(symbol);
        stock.setLastTradePriceOnly("13.98");
        return stock;
    }

}
