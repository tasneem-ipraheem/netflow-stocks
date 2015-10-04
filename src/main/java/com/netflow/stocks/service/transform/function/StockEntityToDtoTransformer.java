package com.netflow.stocks.service.transform.function;

import com.google.common.base.Function;
import com.netflow.stocks.api.StockDto;
import com.netflow.stocks.data.NetflowStock;
import org.springframework.stereotype.Component;

@Component
public class StockEntityToDtoTransformer implements Function<NetflowStock, StockDto>{

    @Override
    public StockDto apply(NetflowStock netflowStock) {

        StockDto stockDto = new StockDto();
        stockDto.setId(netflowStock.getId());
        stockDto.setSymbol(netflowStock.getSymbol());
        stockDto.setName(netflowStock.getName());
        stockDto.setPrice(netflowStock.getPrice());
        stockDto.setUpdated(netflowStock.getUpdated());

        return stockDto;
    }

}
