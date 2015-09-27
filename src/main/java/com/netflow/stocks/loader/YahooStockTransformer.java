package com.netflow.stocks.loader;

import com.google.common.base.Function;
import com.netflow.stocks.api.Stock;
import org.springframework.stereotype.Component;

@Component
public class YahooStockTransformer implements Function<yahoofinance.Stock, Stock> {

    @Override
    public Stock apply(yahoofinance.Stock yahooStock) {
        Stock stock = new Stock();
        stock.setSymbol(yahooStock.getSymbol());
        stock.setName(yahooStock.getName());
        return stock;
    }

}
