package com.netflow.stocks.service;

import com.netflow.stocks.api.StockDto;
import com.netflow.stocks.api.StocksApi;
import com.netflow.stocks.data.NetflowStockRepository;
import com.netflow.stocks.data.NetflowStock;
import com.netflow.stocks.service.load.NetflowStockLoader;
import com.netflow.stocks.service.transform.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StocksService implements StocksApi {

    @Autowired
    private NetflowStockRepository netflowStockRepository;

    @Autowired
    private NetflowStockLoader netflowStockLoader;

    @Autowired
    private Transformer transformer;

    public StockDto getStockBySymbol(String symbol) {
        NetflowStock netflowStock = netflowStockRepository.findOneBySymbol(symbol);
        if (netflowStock == null) {
            netflowStock = netflowStockLoader.getNetflowStock(symbol);
            netflowStock = netflowStockRepository.saveAndFlush(netflowStock);
        }
        StockDto stockDto = transformer.entityToDto(netflowStock);
        return stockDto;
    }

}