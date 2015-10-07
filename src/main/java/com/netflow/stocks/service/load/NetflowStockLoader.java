package com.netflow.stocks.service.load;

import com.netflow.stocks.data.NetflowStock;
import com.netflow.stocks.service.load.yahoo.YahooStockTransformer;
import com.netflow.stocks.service.load.yahoo.YahooStocksWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

@Component
public class NetflowStockLoader {

    private static final String STOCK_UNKNOWN = "N/A";

    @Autowired
    private YahooStocksWrapper yahooStocksWrapper;
    @Autowired
    private YahooStockTransformer yahooStockTransformer;

    public NetflowStock getNetflowStock(String stockSymbol) {

        Stock stock = yahooStocksWrapper.getStockBySymbol(stockSymbol);
        validateStock(stockSymbol, stock);
        NetflowStock netflowStock = yahooStockTransformer.apply(stock);
        return netflowStock;

    }

    private void validateStock(String stockSymbol, Stock stock) {
        if (stock == null || STOCK_UNKNOWN.equals(stock.getName())) {
            throw new IllegalStateException("Stock '" + stockSymbol + "' is unknown");
        }

    }

}
