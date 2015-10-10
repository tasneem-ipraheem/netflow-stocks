package com.netflow.stocks.service.load;

import com.netflow.stocks.data.NetflowStock;
import com.netflow.stocks.service.load.yahoo.YahooStocksWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NetflowStockLoader {

    @Autowired
    private YahooStocksWrapper yahooStocksWrapper;

    public NetflowStock getNetflowStock(String stockSymbol) {
        NetflowStock netflowStock = yahooStocksWrapper.getStockBySymbol(stockSymbol);
        return netflowStock;
    }


}
