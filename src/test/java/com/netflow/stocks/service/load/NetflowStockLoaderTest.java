package com.netflow.stocks.service.load;

import com.netflow.stocks.data.NetflowStock;
import com.netflow.stocks.service.load.yahoo.YahooStocksWrapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.fest.assertions.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NetflowStockLoaderTest {

    @InjectMocks
    private NetflowStockLoader netflowStockLoader;
    @Mock
    private YahooStocksWrapper yahooStocksWrapper;
    @Mock
    private NetflowStock netflowStockMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetNetflowStock() throws Exception {

        when(yahooStocksWrapper.getStockBySymbol("AAPL")).thenReturn(netflowStockMock);

        NetflowStock resolvedNetflowStock = netflowStockLoader.getNetflowStock("AAPL");

        assertThat(resolvedNetflowStock).isSameAs(netflowStockMock);
    }


}