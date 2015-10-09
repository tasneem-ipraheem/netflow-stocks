package com.netflow.stocks.service.load;

import com.netflow.stocks.data.NetflowStock;
import com.netflow.stocks.service.load.yahoo.YahooAsset;
import com.netflow.stocks.service.load.yahoo.YahooStockTransformer;
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
    private YahooStockTransformer yahooStockTransformer;
    @Mock
    private YahooAsset yahooAsset;
    @Mock
    private NetflowStock netflowStock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetNetflowStock() throws Exception {

        when(yahooStocksWrapper.getStockBySymbol("AAPL")).thenReturn(yahooAsset);
        when(yahooStockTransformer.apply(yahooAsset)).thenReturn(netflowStock);

        NetflowStock resolvedNetflowStock = netflowStockLoader.getNetflowStock("AAPL");

        assertThat(resolvedNetflowStock).isSameAs(netflowStock);
    }

    @Test(expected = IllegalStateException.class)
    public void testGetNetflowStockWhenNameIsNotKnown() throws Exception {

        when(yahooStocksWrapper.getStockBySymbol("UNK")).thenReturn(yahooAsset);
        when(yahooAsset.getName()).thenReturn("N/A");

        netflowStockLoader.getNetflowStock("UNK");
    }

}