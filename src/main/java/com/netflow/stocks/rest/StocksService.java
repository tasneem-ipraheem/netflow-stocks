package com.netflow.stocks.rest;

import com.netflow.stocks.api.StockDto;
import com.netflow.stocks.api.StocksApi;
import org.springframework.stereotype.Component;

@Component
public class StocksService implements StocksApi {

    public StockDto getStockBySymbol(String stockId) {
        return new StockDto();
    }

}