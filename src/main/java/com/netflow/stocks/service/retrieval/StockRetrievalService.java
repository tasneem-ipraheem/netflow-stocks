package com.netflow.stocks.service.retrieval;

import com.netflow.stocks.api.StockDto;
import com.netflow.stocks.data.NetflowStockRepository;
import com.netflow.stocks.data.NetflowStock;
import com.netflow.stocks.service.retrieval.transform.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StockRetrievalService {

    @Autowired
    private NetflowStockRepository netflowStockRepository;
    @Autowired
    private NetflowStockLoader netflowStockLoader;
    @Autowired
    private Transformer transformer;

    @Transactional
    public StockDto getStockBySymbol(String symbol) {
        NetflowStock netflowStock = netflowStockRepository.findOneBySymbol(symbol);
        if (netflowStock == null) {
            netflowStock = netflowStockLoader.getNetflowStock(symbol);
            netflowStock = netflowStockRepository.save(netflowStock);
        }
        StockDto stockDto = transformer.entityToDto(netflowStock);
        return stockDto;
    }

}