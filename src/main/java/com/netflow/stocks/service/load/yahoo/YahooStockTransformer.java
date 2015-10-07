package com.netflow.stocks.service.load.yahoo;

import com.google.common.base.Function;
import com.netflow.stocks.data.NetflowStock;
import com.netflow.stocks.service.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

@Component
public class YahooStockTransformer implements Function<Stock, NetflowStock> {

    @Autowired
    private DateUtils dateUtils;

    @Override
    public NetflowStock apply(Stock yahooStock) {
        NetflowStock netflowStock = new NetflowStock();
        netflowStock.setSymbol(yahooStock.getSymbol());
        netflowStock.setName(yahooStock.getName());
        netflowStock.setPrice(yahooStock.getQuote().getPrice());
        netflowStock.setUpdated(dateUtils.now());
        return netflowStock;
    }

}
