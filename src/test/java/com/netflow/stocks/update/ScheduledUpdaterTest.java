package com.netflow.stocks.update;

import com.google.common.collect.Lists;
import com.netflow.stocks.data.NetflowStock;
import com.netflow.stocks.data.NetflowStockRepository;
import com.netflow.stocks.service.retrieval.NetflowStockLoader;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class ScheduledUpdaterTest {

    @InjectMocks
    private ScheduledUpdater scheduledUpdater;
    @Mock
    private EntityManager entityManager;
    @Mock
    private NetflowStockRepository netflowStockRepository;
    @Mock
    private NetflowStockLoader netflowStockLoader;
    @Mock
    private NetflowStock netflowStock;
    @Mock
    private NetflowStock detachedNetflowStock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateStocksDataWithNoStocks() throws Exception {
        when(netflowStockRepository.findAll()).thenReturn(Lists.newArrayList());
        scheduledUpdater.updateStocksData();
        verifyZeroInteractions(netflowStockLoader);
    }

    @Test
    public void testUpdateStock() throws Exception {

        when(netflowStockRepository.findAll()).thenReturn(Lists.newArrayList(netflowStock));
        when(netflowStock.getId()).thenReturn(1L);
        when(netflowStock.getSymbol()).thenReturn("ed");
        when(netflowStockLoader.getNetflowStock("ed")).thenReturn(detachedNetflowStock);

        scheduledUpdater.updateStocksData();

        verify(detachedNetflowStock).setId(1L);
        verify(entityManager).merge(detachedNetflowStock);

    }
}
