package com.netflow.stocks.service.retrieval.transform;

import com.netflow.stocks.api.StockDto;
import com.netflow.stocks.data.NetflowStock;
import com.netflow.stocks.service.retrieval.transform.function.StockEntityToDtoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransformerImpl implements Transformer {

    @Autowired
    private StockEntityToDtoTransformer stockEntityToDtoTransformer;

    @Override
    public StockDto entityToDto(NetflowStock netflowStock) {
        StockDto stockDto = stockEntityToDtoTransformer.apply(netflowStock);
        return stockDto;
    }

}
