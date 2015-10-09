package com.netflow.stocks.service.load.yahoo;

import com.google.common.base.Function;
import com.netflow.stocks.data.NetflowStock;
import com.netflow.stocks.service.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class YahooStockTransformer implements Function<YahooAsset, NetflowStock> {

    @Autowired
    private DateUtils dateUtils;

    @Override
    public NetflowStock apply(YahooAsset yahooAsset) {
        NetflowStock netflowStock = new NetflowStock();
        netflowStock.setSymbol(yahooAsset.getSymbol());
        netflowStock.setName(yahooAsset.getName());
        netflowStock.setPrice(new BigDecimal(yahooAsset.getLastTradePriceOnly()));
        netflowStock.setUpdated(dateUtils.now());
        return netflowStock;
    }

}
