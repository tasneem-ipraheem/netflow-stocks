package com.netflow.stocks.service.retrieval.transform;

import com.netflow.stocks.api.StockDto;
import com.netflow.stocks.data.NetflowStock;

public interface Transformer {

    StockDto entityToDto(NetflowStock netflowStock);

}
