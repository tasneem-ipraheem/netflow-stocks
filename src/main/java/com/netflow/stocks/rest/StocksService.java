package com.netflow.stocks.rest;

import com.netflow.stocks.api.StockDto;
import com.netflow.stocks.api.StocksApi;
import com.netflow.stocks.data.NetflowStockRepository;
import com.netflow.stocks.data.NetflowStock;
import com.netflow.stocks.rest.transform.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StocksService implements StocksApi {

    @Autowired
    private NetflowStockRepository netflowStockRepository;
    @Autowired
    private Transformer transformer;

    public StockDto getStockBySymbol(String symbol) {
        NetflowStock netflowStock = netflowStockRepository.findOneBySymbol(symbol);
        StockDto stockDto = transformer.entityToDto(netflowStock);
        return stockDto;
    }

}