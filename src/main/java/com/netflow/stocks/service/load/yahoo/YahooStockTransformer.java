package com.netflow.stocks.service.load.yahoo;

import com.google.common.base.Function;
import com.netflow.stocks.data.NetflowStock;
import com.netflow.stocks.service.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class YahooStockTransformer implements Function<YahooQuote, NetflowStock> {

    @Autowired
    private DateUtils dateUtils;

    @Override
    public NetflowStock apply(YahooQuote yahooQuote) {
        NetflowStock netflowStock = new NetflowStock();
        netflowStock.setSymbol(yahooQuote.getSymbol());
        netflowStock.setName(yahooQuote.getName());
        netflowStock.setPrice(new BigDecimal(yahooQuote.getLastTradePriceOnly()));
        netflowStock.setUpdated(dateUtils.now());
        return netflowStock;
    }

}
