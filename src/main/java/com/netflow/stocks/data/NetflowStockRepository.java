package com.netflow.stocks.data;

import org.springframework.data.repository.CrudRepository;

public interface NetflowStockRepository extends CrudRepository<NetflowStock, Long> {

    public NetflowStock findOneBySymbol(String symbol);

}
