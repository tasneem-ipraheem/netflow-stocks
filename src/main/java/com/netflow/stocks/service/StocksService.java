package com.netflow.stocks.service;

import com.netflow.stocks.api.LookupResultDto;
import com.netflow.stocks.api.StockDto;
import com.netflow.stocks.api.StocksApi;
import com.netflow.stocks.service.retrieval.StockRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StocksService implements StocksApi {

    @Autowired
    private StockRetrievalService stockRetrievalService;

    @Override
    public StockDto getStockBySymbol(String stockId) {
        StockDto stockDto = stockRetrievalService.getStockBySymbol(stockId);
        return stockDto;
    }

    @Override
    public Collection<LookupResultDto> lookupSymbolByName(String name) {
        return null;
    }
}
