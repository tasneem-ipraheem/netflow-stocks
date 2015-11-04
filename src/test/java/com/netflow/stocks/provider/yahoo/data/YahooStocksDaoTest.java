package com.netflow.stocks.provider.yahoo.data;

import com.netflow.stocks.data.NetflowStock;
import com.netflow.stocks.statistics.Provider;
import com.netflow.stocks.statistics.StatsService;
import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.web.client.RestTemplate;

import static org.fest.assertions.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
public class YahooStocksDaoTest {

    @InjectMocks
    private YahooStocksDao yahooStocksDao;
    @Mock
    private RestTemplate yahooRestTemplate;
    @Mock
    private YahooStockTransformer yahooStockTransformer;
    @Mock
    private NetflowStock netflowStockMock;
    @Mock
    private StatsService statsService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        yahooStocksDao.postConstruct();
    }

    @Test
    public void testGetStockBySymbol() throws Exception {

        when(yahooRestTemplate.getForObject(any(String.class), eq(YahooFinanceResponse.class)))
                .thenReturn(YahooFinanceResponseStubs.stubYahooResponseWithQuoteKnown("AAPL"));
        when(yahooStockTransformer.apply(any(YahooQuote.class))).thenReturn(netflowStockMock);

        NetflowStock netflowStock = yahooStocksDao.getStockBySymbol("AAPL");
        assertThat(netflowStock).isSameAs(netflowStockMock);
        verify(statsService).logSuccess(Provider.YAHOO_DATA, 301);
    }

}