package com.netflow.stocks.service.load;

import com.netflow.stocks.data.NetflowStock;
import com.netflow.stocks.service.load.yahoo.YahooStockTransformer;
import com.netflow.stocks.service.load.yahoo.YahooStocksWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

@Component
public class NetflowStockLoader {

    @Autowired
    private YahooStocksWrapper yahooStocksWrapper;
    @Autowired
    private YahooStockTransformer yahooStockTransformer;

    public NetflowStock getNetflowStock(String stockSymbol) {

        Stock stock = yahooStocksWrapper.getStockBySymbol(stockSymbol);
        NetflowStock netflowStock = yahooStockTransformer.apply(stock);
        return netflowStock;
        
    }

}
