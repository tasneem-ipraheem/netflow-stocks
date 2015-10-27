package com.netflow.stocks.service.load.yahoo;

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
public class YahooStocksClientTest {

    @InjectMocks
    private YahooStocksClient yahooStocksClient;
    @Mock
    private RestTemplate yahooRestTemplate;
    @Mock
    private YahooStockTransformer yahooStockTransformer;
    @Mock
    private NetflowStock netflowStockMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        yahooStocksClient.postConstruct();
    }

    @Test
    public void testGetStockBySymbol() throws Exception {

        when(yahooRestTemplate.getForObject(any(String.class), eq(YahooFinanceResponse.class)))
                .thenReturn(YahooFinanceResponseStubs.stubYahooResponseWithQuoteKnown("AAPL"));
        when(yahooStockTransformer.apply(any(YahooQuote.class))).thenReturn(netflowStockMock);

        NetflowStock netflowStock = yahooStocksClient.getStockBySymbol("AAPL");
        Assertions.assertThat(netflowStock).isSameAs(netflowStockMock);
    }

}