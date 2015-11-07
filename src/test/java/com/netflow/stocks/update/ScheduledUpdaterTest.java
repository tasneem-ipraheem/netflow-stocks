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
    private NetflowStock netflowStock1;
    @Mock
    private NetflowStock netflowStock2;
    @Mock
    private NetflowStock detachedNetflowStock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(netflowStock1.getId()).thenReturn(1L);
        when(netflowStock1.getSymbol()).thenReturn("ed");
        when(netflowStock2.getId()).thenReturn(2L);
        when(netflowStock2.getSymbol()).thenReturn("mock");
    }

    @Test
    public void testUpdateStocksDataWithNoStocks() throws Exception {
        when(netflowStockRepository.findAll()).thenReturn(Lists.newArrayList());
        scheduledUpdater.updateStocksData();
        verifyZeroInteractions(netflowStockLoader);
    }

    @Test
    public void testUpdateStock() throws Exception {

        when(netflowStockRepository.findAll()).thenReturn(Lists.newArrayList(netflowStock1));
        when(netflowStockLoader.getNetflowStock("ed")).thenReturn(detachedNetflowStock);

        scheduledUpdater.updateStocksData();

        verify(detachedNetflowStock).setId(1L);
        verify(entityManager).merge(detachedNetflowStock);

    }

    @Test
    public void testUpdateStocksWithOneFailing() throws Exception {

        when(netflowStockRepository.findAll()).thenReturn(Lists.newArrayList(netflowStock1, netflowStock2));
        when(netflowStockLoader.getNetflowStock("ed")).thenThrow(new IllegalStateException());
        when(netflowStockLoader.getNetflowStock("mock")).thenReturn(detachedNetflowStock);

        scheduledUpdater.updateStocksData();

        verify(detachedNetflowStock).setId(2L);
        verify(entityManager).merge(detachedNetflowStock);

    }
}
