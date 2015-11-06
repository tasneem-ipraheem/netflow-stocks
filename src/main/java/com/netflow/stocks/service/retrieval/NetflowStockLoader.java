package com.netflow.stocks.service.retrieval;

import com.netflow.stocks.data.NetflowStock;
import com.netflow.stocks.provider.yahoo.data.YahooStocksDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NetflowStockLoader {

    private static Logger logger = LoggerFactory.getLogger(NetflowStockLoader.class);

    @Autowired
    private YahooStocksDao yahooStocksDao;

    public NetflowStock getNetflowStock(String stockSymbol) {
        NetflowStock netflowStock = yahooStocksDao.getStockBySymbol(stockSymbol);
        logger.info("Stock " + stockSymbol + " data retrieved: " + netflowStock);
        return netflowStock;
    }


}
