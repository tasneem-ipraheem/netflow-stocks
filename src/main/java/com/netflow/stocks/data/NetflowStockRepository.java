package com.netflow.stocks.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NetflowStockRepository extends JpaRepository<NetflowStock, Long> {

    NetflowStock findOneBySymbol(String symbol);

}
