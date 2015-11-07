package com.netflow.stocks.provider.yahoo.data;

import com.netflow.stocks.data.NetflowStock;
import com.netflow.stocks.statistics.Provider;
import com.netflow.stocks.statistics.StatsService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.fest.assertions.api.Assertions.*;
import static org.mockito.Mockito.*;

public class YahooStocksDaoTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
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

    @Test
    public void testGetStockBySymbolWithNullQuery() throws Exception {

        when(yahooRestTemplate.getForObject(any(String.class), eq(YahooFinanceResponse.class)))
                .thenReturn(new YahooFinanceResponse());

        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Stock 'AAPL' request results invalid, null response query");

        yahooStocksDao.getStockBySymbol("AAPL");

    }

    @Test
    public void testGetStockBySymbolWithNullResult() throws Exception {

        YahooFinanceResponse yahooFinanceResponse = new YahooFinanceResponse();
        yahooFinanceResponse.setQuery(new YahooQuery());
        when(yahooRestTemplate.getForObject(any(String.class), eq(YahooFinanceResponse.class)))
                .thenReturn(yahooFinanceResponse);

        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Stock 'AAPL' request results invalid, null results");
        yahooStocksDao.getStockBySymbol("AAPL");

    }

    @Test
    public void testGetStockBySymbolWithNullQuote() throws Exception {

        YahooFinanceResponse yahooFinanceResponse = new YahooFinanceResponse();
        YahooQuery yahooQuery = new YahooQuery();
        yahooFinanceResponse.setQuery(yahooQuery);
        yahooQuery.setResults(new YahooResults());

        when(yahooRestTemplate.getForObject(any(String.class), eq(YahooFinanceResponse.class)))
                .thenReturn(yahooFinanceResponse);

        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Stock 'AAPL' request results invalid, null quote");
        yahooStocksDao.getStockBySymbol("AAPL");

    }


    @Test
    public void testGetStockBySymbolWithNullName() throws Exception {

        YahooFinanceResponse yahooFinanceResponse = new YahooFinanceResponse();
        YahooQuery yahooQuery = new YahooQuery();
        yahooFinanceResponse.setQuery(yahooQuery);
        YahooResults yahooResults = new YahooResults();
        yahooQuery.setResults(yahooResults);
        yahooResults.setQuote(new YahooQuote());

        when(yahooRestTemplate.getForObject(any(String.class), eq(YahooFinanceResponse.class)))
                .thenReturn(yahooFinanceResponse);

        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Stock 'AAPL' request results invalid");
        yahooStocksDao.getStockBySymbol("AAPL");

    }
}