package com.netflow.stocks.service.update;

import com.netflow.stocks.data.NetflowStock;
import com.netflow.stocks.data.NetflowStockRepository;
import com.netflow.stocks.service.load.NetflowStockLoader;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class ScheduledUpdater {

    private static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ScheduledUpdater.class);

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private NetflowStockRepository netflowStockRepository;
    @Autowired
    private NetflowStockLoader netflowStockLoader;

    @Scheduled(cron = "${netflow.stocks.scheduler.cron}")
    public void updateStocksData() {

        List<NetflowStock> netflowStocks = netflowStockRepository.findAll();

        if (CollectionUtils.isEmpty(netflowStocks)) {
            return;
        }

        for (NetflowStock netflowStock : netflowStocks) {
            updateStock(netflowStock.getId(), netflowStock.getSymbol());
        }

    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void updateStock(long id, String symbol) {
        LOGGER.info("updating stock: " + symbol);
        NetflowStock detachedNetflowStock = netflowStockLoader.getNetflowStock(symbol);
        detachedNetflowStock.setId(id);
        entityManager.merge(detachedNetflowStock);
    }


}
