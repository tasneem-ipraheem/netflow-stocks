package com.netflow.stocks.service.load.yahoo;

import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Service
public class YahooStocksWrapper {

    private static final String STOCK_UNKNOWN = "N/A";

    public Stock getStockBySymbol(String stockSymbol) {

        try {

            Stock stock = YahooFinance.get(stockSymbol);

            if (stock == null || stock.getName().equals(STOCK_UNKNOWN)) {
                throw new YahooStocksException("Stock '" + stockSymbol + "' is unknown");
            }

            return stock;

        } catch (Exception e) {
            throw new YahooStocksException("Could not load '" + stockSymbol + "' stock", e);
        }

    }
}
