package com.netflow.stocks.service.load;

import com.netflow.stocks.data.NetflowStock;
import com.netflow.stocks.service.load.yahoo.YahooStocksWrapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NetflowStockLoader {

    private static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(NetflowStockLoader.class);

    @Autowired
    private YahooStocksWrapper yahooStocksWrapper;

    public NetflowStock getNetflowStock(String stockSymbol) {
        NetflowStock netflowStock = yahooStocksWrapper.getStockBySymbol(stockSymbol);
        LOGGER.info("Stock " + stockSymbol + " data retrieved: " + netflowStock);
        return netflowStock;
    }


}
