package com.netflow.stocks.loader;

import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;

@Service
public class YahooStocksWrapper {

    public yahoofinance.Stock getStockBySymbol(String stockSymbol) {

        try {

            yahoofinance.Stock stock = YahooFinance.get(stockSymbol);
            return stock;

        } catch (Exception e) {
            throw new StocksLoaderException("Could not load '" + stockSymbol + "' stock", e);
        }

    }
}
