package com.netflow.stocks.loader;

import com.google.common.base.Function;
import com.netflow.stocks.api.StockDto;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

@Component
public class YahooStockTransformer implements Function<Stock, StockDto> {

    @Override
    public StockDto apply(Stock yahooStock) {
        StockDto stockDto = new StockDto();
        stockDto.setSymbol(yahooStock.getSymbol());
        stockDto.setName(yahooStock.getName());
        return stockDto;
    }

}
