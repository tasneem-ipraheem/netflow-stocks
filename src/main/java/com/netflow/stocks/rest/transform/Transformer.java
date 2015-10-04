package com.netflow.stocks.rest.transform;

import com.netflow.stocks.api.StockDto;
import com.netflow.stocks.data.NetflowStock;

public interface Transformer {

    StockDto entityToDto(NetflowStock netflowStock);

}
