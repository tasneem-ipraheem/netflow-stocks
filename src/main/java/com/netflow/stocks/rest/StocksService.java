package com.netflow.stocks.rest;

import com.netflow.stocks.api.Stock;
import com.netflow.stocks.api.StocksApi;
import org.springframework.stereotype.Component;

@Component
public class StocksService implements StocksApi {

    public Stock getStockBySymbol(String stockId) {
        return new Stock();
    }

}