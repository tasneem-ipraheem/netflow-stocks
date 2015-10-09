package com.netflow.stocks.service.load;

import com.netflow.stocks.data.NetflowStock;
import com.netflow.stocks.service.load.yahoo.YahooAsset;
import com.netflow.stocks.service.load.yahoo.YahooStockTransformer;
import com.netflow.stocks.service.load.yahoo.YahooStocksWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NetflowStockLoader {

    private static final String STOCK_UNKNOWN = "N/A";

    @Autowired
    private YahooStocksWrapper yahooStocksWrapper;
    @Autowired
    private YahooStockTransformer yahooStockTransformer;

    public NetflowStock getNetflowStock(String stockSymbol) {

        YahooAsset yahooAsset = yahooStocksWrapper.getStockBySymbol(stockSymbol);
        validateStock(stockSymbol, yahooAsset);
        NetflowStock netflowStock = yahooStockTransformer.apply(yahooAsset);
        return netflowStock;

    }

    private void validateStock(String stockSymbol, YahooAsset yahooAsset) {
        if (yahooAsset == null || STOCK_UNKNOWN.equals(yahooAsset.getName())) {
            throw new IllegalStateException("Stock '" + stockSymbol + "' is unknown");
        }

    }

}
