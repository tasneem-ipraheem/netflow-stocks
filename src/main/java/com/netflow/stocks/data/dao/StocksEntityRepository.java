package com.netflow.stocks.data.dao;

import com.netflow.stocks.data.model.StockEntity;
import org.springframework.data.repository.CrudRepository;

public interface StocksEntityRepository extends CrudRepository<StockEntity, Long> {

    public StockEntity findOneById(final long id);

    public StockEntity findOneBySymbol(final String symbol);

}
