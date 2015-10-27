package com.netflow.stocks.service.load;

import com.netflow.stocks.data.NetflowStock;
import com.netflow.stocks.service.load.yahoo.YahooStocksClient;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NetflowStockLoader {

    private static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(NetflowStockLoader.class);

    @Autowired
    private YahooStocksClient yahooStocksClient;

    public NetflowStock getNetflowStock(String stockSymbol) {
        NetflowStock netflowStock = yahooStocksClient.getStockBySymbol(stockSymbol);
        LOGGER.info("Stock " + stockSymbol + " data retrieved: " + netflowStock);
        return netflowStock;
    }


}
