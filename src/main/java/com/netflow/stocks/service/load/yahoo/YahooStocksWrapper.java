package com.netflow.stocks.service.load.yahoo;

import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Service
public class YahooStocksWrapper {

    public Stock getStockBySymbol(String stockSymbol) {

        try {

            Stock stock = YahooFinance.get(stockSymbol);

            if (stock == null || stock.getName().equals("N/A")) {
                throw new YahooStocksException("Could not load '" + stockSymbol + "' stock");
            }

            return stock;

        } catch (Exception e) {
            throw new YahooStocksException("Could not load '" + stockSymbol + "' stock", e);
        }

    }
}