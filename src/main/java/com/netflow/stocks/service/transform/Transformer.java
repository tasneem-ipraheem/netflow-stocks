package com.netflow.stocks.service.transform;

import com.netflow.stocks.api.StockDto;
import com.netflow.stocks.data.NetflowStock;

public interface Transformer {

    StockDto entityToDto(NetflowStock netflowStock);

}
