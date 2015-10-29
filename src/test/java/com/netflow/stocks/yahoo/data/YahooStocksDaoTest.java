package com.netflow.stocks.yahoo.data;

import com.netflow.stocks.data.NetflowStock;
import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.web.client.RestTemplate;

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
        Assertions.assertThat(netflowStock).isSameAs(netflowStockMock);
    }

}