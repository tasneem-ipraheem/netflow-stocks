package com.netflow.stocks.loader;

import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Service
public class YahooStocksWrapper {

    public Stock getStockBySymbol(String stockSymbol) {

        try {

            Stock stock = YahooFinance.get(stockSymbol);
            return stock;

        } catch (Exception e) {
            throw new StocksLoaderException("Could not load '" + stockSymbol + "' stock", e);
        }

    }
}
